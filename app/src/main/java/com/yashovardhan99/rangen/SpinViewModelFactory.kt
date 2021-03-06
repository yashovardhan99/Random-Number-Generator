package com.yashovardhan99.rangen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Yashovardhan99 on 6/6/20 as a part of Random Number Generator.
 */
class SpinViewModelFactory(
    private val start: Int,
    private val end: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RollViewModel(RollOption.SPIN, start, end) as T
    }
}