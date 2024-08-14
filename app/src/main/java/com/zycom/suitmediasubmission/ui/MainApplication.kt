package com.zycom.suitmediasubmission.ui

import android.app.Application
import com.zycom.suitmediasubmission.data.di.Injection

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        injection = Injection(applicationContext)
    }

    companion object {
        lateinit var injection: Injection
    }
}