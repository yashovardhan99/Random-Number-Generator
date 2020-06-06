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
import com.yashovardhan99.rangen.databinding.FragmentSelectBinding
import timber.log.Timber

class SelectFragment : Fragment() {

    private val viewModel: SelectViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentSelectBinding>(
            inflater,
            R.layout.fragment_select,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        viewModel.goToFragment.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                Timber.d("goToFragment is $it")
                return@Observer
            }
            viewModel.navigationDone()
            findNavController().navigate(
                when (it) {
                    NavOptions.SINGLE_DIE -> SelectFragmentDirections.actionSelectFragmentToRollDiceFragment()
                    NavOptions.DOUBLE_DICE -> SelectFragmentDirections.actionSelectFragmentToRoll2DiceFragment()
                    else -> SelectFragmentDirections.actionSelectFragmentToSelectFragment2()
                }
            )
        })
        return binding.root
    }
}