package com.example.daggerlogin.login

class LoginActivityPresenter(private val model: LoginActivityMVP.Model) : LoginActivityMVP.Presenter {

    private lateinit var view: LoginActivityMVP.View

    override fun setView(view: LoginActivityMVP.View) {
        this.view = view
    }

    override fun loginButtonClicked() {
        if (view.getFirstName().trim() == "" || view.getLastName().trim() == "")
            view.showInputError()
        else
        {
            model.createUser(view.getFirstName().trim(), view.getLastName().trim())
            view.showUserSave()
        }
    }

    override fun getCurrentUser() {
        val user = model.getUser()
        view.setFirstName(user.firstName)
        view.setLastName(user.lastName)
    }
}