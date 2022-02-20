package com.example.yarmarka

import android.app.Application
import com.example.yarmarka.di.AppComponent
import com.example.yarmarka.di.DaggerAppComponent

class App: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}