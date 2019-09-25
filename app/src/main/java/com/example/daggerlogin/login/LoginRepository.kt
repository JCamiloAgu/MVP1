package com.example.daggerlogin.login

interface LoginRepository{
    fun saveUser()

    fun getUser(): User
}