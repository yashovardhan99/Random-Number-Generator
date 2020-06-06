package com.yashovardhan99.rangen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yashovardhan99.rangen.databinding.FragmentDiceRollerBinding

/**
 * Created by Yashovardhan99 on 5/6/20 as a part of Random Number Generator.
 */
class RollDiceFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDiceRollerBinding>(
            inflater,
            R.layout.fragment_dice_roller,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        val viewModelFactory = RollViewModelFactory(RollOption.SINGLE_DIE)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(RollViewModel::class.java)
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