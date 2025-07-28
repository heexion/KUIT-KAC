package com.konkuk.kuit_kac

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NyamApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}