package com.example.daggerlogin.root

import com.example.daggerlogin.http.TwitchModule
import com.example.daggerlogin.login.LoginActivity
import com.example.daggerlogin.login.LoginModule
import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [ApplicationModule::class, LoginModule::class, TwitchModule::class])
interface ApplicationComponent {
    fun inject(target: LoginActivity)
}
