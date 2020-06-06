package com.yashovardhan99.rangen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.random.Random

/**
 * Created by Yashovardhan99 on 5/6/20 as a part of Random Number Generator.
 */
enum class RollOption {
    SINGLE_DIE,
    DOUBLE_DIE,
    SPIN
}

class RollViewModel(
    selectedOption: RollOption,
    private val startSpin: Int = 1,
    private val endSpin: Int = 6
) :
    ViewModel() {
    private val _currentRoll = MutableLiveData<String>()
    val currentRoll: LiveData<String>
        get() = _currentRoll
    private val _currentSecondRoll = MutableLiveData<String>()
    val currentSecondRoll: LiveData<String>
        get() = _currentSecondRoll
    private val _isSpinning = MutableLiveData<Boolean>()
    val isSpinning: LiveData<Boolean>
        get() = _isSpinning
    private val _rollText = MutableLiveData<Int>()
    val rollText: LiveData<Int>
        get() = _rollText
    private val _isDouble = MutableLiveData<Boolean>()
    val isDouble: LiveData<Boolean> = _isDouble
    private val _backNav = MutableLiveData(false)
    val backNav: LiveData<Boolean> = _backNav
    private val delay = 250L
    private var job: Job = Job()

    init {
        _isSpinning.value = true
        _rollText.value = when (selectedOption) {
            RollOption.SINGLE_DIE -> R.string.tap_to_roll_a_die
            RollOption.DOUBLE_DIE -> R.string.tap_to_roll_2_dice
            RollOption.SPIN -> R.string.tap_to_spin_a_wheel
        }
    }

    private fun timeoutSpin(isDouble: Boolean = false) {
        job.cancel()
        job = viewModelScope.launch {
            delay(delay)
            Timber.d("Resetting isSpinning")
            withContext(Dispatchers.Main) {
                _isSpinning.value = false
                _isDouble.value = isDouble
            }
        }
    }

    fun rollDie() {
        _rollText.value = R.string.rolling
        Timber.d("Roll Die called")
        _isSpinning.value = true
        _currentRoll.value = Random.nextInt(1, 7).toString()
        Timber.d("Current roll is ${currentRoll.value}")
        timeoutSpin()
    }

    fun roll2Dice() {
        _isDouble.value = false
        _rollText.value = R.string.rolling
        _isSpinning.value = true
        _currentRoll.value = Random.nextInt(1, 7).toString()
        _currentSecondRoll.value = Random.nextInt(1, 7).toString()
        timeoutSpin(_currentRoll.value == _currentSecondRoll.value)
    }

    fun spin() {
        _rollText.value = R.string.spinning
        _isSpinning.value = true
        _currentRoll.value = Random.nextInt(startSpin, endSpin + 1).toString()
        timeoutSpin()
    }

    fun onBackPressed() {
        _backNav.value = true
    }

    fun onNavigationDone() {
        _backNav.value = false
    }
}