package com.example.daggerlogin.login

interface LoginActivityMVP
{
    interface View
    {
        fun getFirstName(): String
        fun getLastName(): String

        fun showUserNotAvailable()
        fun showInputError()
        fun showUserSave()

        fun setFirstName(firstName: String)
        fun setLastName(lastName: String)
    }

    interface Presenter
    {
        fun setView(view: View)

        fun loginButtonClicked()

        fun getCurrentUser()
    }

    interface Model
    {
        fun createUser(firstName: String, lastName: String)

        fun getUser(): User
    }
}