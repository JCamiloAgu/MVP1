package com.example.daggerlogin.root

import com.example.daggerlogin.login.LoginActivity
import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(target: LoginActivity)
}
