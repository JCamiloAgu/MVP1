package com.example.daggerlogin.login

interface LoginRepository{
    fun saveUser(user: User)

    fun getUser(): User
}