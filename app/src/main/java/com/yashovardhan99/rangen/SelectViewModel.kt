package com.yashovardhan99.rangen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Yashovardhan99 on 5/6/20 as a part of Random Number Generator.
 */
enum class NavOptions {
    SINGLE_DIE, DOUBLE_DICE, SPIN_WHEEL, SELECT_SPIN, BACK
}

class SelectViewModel : ViewModel() {
    private val _goToFragment = MutableLiveData<NavOptions>()
    val goToFragment: LiveData<NavOptions>
        get() = _goToFragment
    private val _errorEnabled = MutableLiveData<Boolean>()
    val errorEnabled: LiveData<Boolean>
        get() = _errorEnabled
    var start: String = "1"
    var end: String = "10"

    fun goToSingleDie() {
        _goToFragment.value = NavOptions.SINGLE_DIE
    }

    fun navigationDone() {
        _goToFragment.value = null
    }

    fun goToDoubleDice() {
        _goToFragment.value = NavOptions.DOUBLE_DICE
    }

    fun goToSpin() {
        resetError()
        if (start.toLong() < end.toLong())
            _goToFragment.value = NavOptions.SPIN_WHEEL
        else
            _errorEnabled.value = true
    }

    fun goToSelectSpin() {
        _goToFragment.value = NavOptions.SELECT_SPIN
    }

    private fun resetError() {
        _errorEnabled.value = false
    }

    fun onBackPressed() {
        _goToFragment.value = NavOptions.BACK
    }
}