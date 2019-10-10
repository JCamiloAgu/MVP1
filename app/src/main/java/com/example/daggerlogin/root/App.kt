package com.example.daggerlogin.root

import android.app.Application
import com.example.daggerlogin.http.TwitchModule
import com.example.daggerlogin.login.LoginModule

class App : Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .loginModule(LoginModule())
            .twitchModule(TwitchModule())
            .build()
    }

    fun getComponent(): ApplicationComponent = component
}
