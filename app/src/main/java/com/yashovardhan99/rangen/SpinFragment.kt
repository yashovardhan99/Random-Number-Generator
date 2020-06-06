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
import com.yashovardhan99.rangen.databinding.FragmentSpinBinding

/**
 * Created by Yashovardhan99 on 6/6/20 as a part of Random Number Generator.
 */
class SpinFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSpinBinding>(
            inflater,
            R.layout.fragment_spin,
            container,
            false
        )
        val args = SpinFragmentArgs.fromBundle(requireArguments())
        val viewModel: RollViewModel by viewModels {
            SpinViewModelFactory(
                args.start, args.end
            )
        }
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        viewModel.backNav.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                viewModel.onNavigationDone()
                findNavController().popBackStack()
            }
        })
        return binding.root
    }
}