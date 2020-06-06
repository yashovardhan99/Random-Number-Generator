package com.yashovardhan99.rangen

import android.app.Application
import timber.log.Timber

/**
 * Created by Yashovardhan99 on 5/6/20 as a part of Random Number Generator.
 */
@Suppress("unused")
class RandomApplication : Application() {
    init {
        Timber.plant(Timber.DebugTree())
    }
}