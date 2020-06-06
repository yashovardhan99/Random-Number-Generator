package com.yashovardhan99.rangen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.yashovardhan99.rangen.databinding.FragmentSelectTwoBinding
import timber.log.Timber

class SelectFragment2 : Fragment() {

    private val viewModel: SelectViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentSelectTwoBinding>(
            inflater,
            R.layout.fragment_select_two,
            container,
            false
        )
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.errorEnabled.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.startText.error = "Must be less than ${viewModel.end}"
                binding.endText.error = "Must be more than ${viewModel.start}"
            } else {
                binding.startText.error = null
                binding.endText.error = null
            }
        })
        viewModel.goToFragment.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                Timber.d("goToFragment is $it")
                return@Observer
            }
            viewModel.navigationDone()
            Timber.d("goToFragment is $it")
            if (it == NavOptions.BACK) {
                findNavController().popBackStack()
                return@Observer
            }
            findNavController().navigate(
                when (it) {
                    NavOptions.SINGLE_DIE -> SelectFragment2Directions.actionSelectFragment2ToRollDiceFragment()
                    NavOptions.DOUBLE_DICE -> SelectFragment2Directions.actionSelectFragment2ToRoll2DiceFragment()
                    else -> SelectFragment2Directions.actionSelectFragment2ToSpinFragment2(
                        viewModel.start.toInt(),
                        viewModel.end.toInt()
                    )
                }
            )
        })
        return binding.root
    }
}