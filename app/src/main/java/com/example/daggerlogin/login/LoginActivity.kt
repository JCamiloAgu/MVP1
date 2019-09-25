package com.example.daggerlogin.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.daggerlogin.root.App
import com.example.daggerlogin.R
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).getComponent().inject(this)

        button_login.setOnClickListener { Toast.makeText(this, "Button pressed", Toast.LENGTH_SHORT).show() }
    }
}
