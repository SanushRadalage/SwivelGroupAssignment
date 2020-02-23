package com.sanushradalage.cinema.extras

import android.app.Application
import android.os.SystemClock

class SplashTime : Application() {

    override fun onCreate() {
        super.onCreate()
        SystemClock.sleep(2000)
    }
}
