package com.example.daggerlogin.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.daggerlogin.root.App
import com.example.daggerlogin.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginActivityMVP.View{

    @Inject
    lateinit var presenter: LoginActivityMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).getComponent().inject(this)

        button_login.setOnClickListener { presenter.loginButtonClicked() }
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.getCurrentUser()
    }

    override fun getFirstName(): String = edit_text_first_name.text.toString()

    override fun getLastName(): String = edit_text_last_name.text.toString()

    override fun showUserNotAvailable() {
        Toast.makeText(this, "El usuario no está disponible", Toast.LENGTH_SHORT).show()
    }

    override fun showInputError() {
        Toast.makeText(this, "Error, el nombre ni el apellido pueden estar vacios", Toast.LENGTH_SHORT).show()
    }

    override fun showUserSave() {
        Toast.makeText(this, "Usuario guardado correctamente", Toast.LENGTH_SHORT).show()
    }

    override fun setFirstName(firstName: String) {
        edit_text_first_name.setText(firstName)
    }

    override fun setLastName(lastName: String) {
        edit_text_last_name.setText(lastName)
    }
}
