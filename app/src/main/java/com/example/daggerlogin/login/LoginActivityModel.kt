package com.example.daggerlogin.login

class LoginActivityModel(private val repository: LoginRepository) : LoginActivityMVP.Model {

    override fun createUser(firstName: String, lastName: String) {
        repository.saveUser(User(0, firstName, lastName))
    }

    override fun getUser(): User = repository.getUser()
}