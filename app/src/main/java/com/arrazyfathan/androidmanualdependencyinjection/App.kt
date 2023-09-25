package com.arrazyfathan.androidmanualdependencyinjection

import android.app.Application
import com.arrazyfathan.androidmanualdependencyinjection.di.AppModule
import com.arrazyfathan.androidmanualdependencyinjection.di.AppModuleImpl

class App : Application() {

    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}