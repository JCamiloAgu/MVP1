package com.example.daggerlogin.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerlogin.http.TwitchAPI
import com.example.daggerlogin.root.App
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class LoginActivity : AppCompatActivity(), LoginActivityMVP.View{

    @Inject
    lateinit var presenter: LoginActivityMVP.Presenter

    @Inject
    lateinit var twitchAPI: TwitchAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.daggerlogin.R.layout.activity_main)

        (application as App).getComponent().inject(this)

        button_login.setOnClickListener { presenter.loginButtonClicked() }

//        //Ejemplo API de Twitch
//        val call: Call<Twitch> = twitchAPI.getTopGames("stlqknzycm6p4lk8srry5g0feg6dai")
//
//        call.enqueue(object: Callback<Twitch>{
//
//            override fun onResponse(call: Call<Twitch>, response: Response<Twitch>) {
//                val topGames: List<Game> = response.body()!!.game
//                topGames.forEach { println("Twitch: ${it.name}") }
//                print("Cantidad: ${topGames.size}")
//            }
//
//            override fun onFailure(call: Call<Twitch>, t: Throwable) {
//                t.stackTrace
//            }
//
//        })

//        twitchAPI.getTopGamesObservable("stlqknzycm6p4lk8srry5g0feg6dai").flatMap { twitch -> Observable.fromIterable(twitch.game)}.
//            flatMap { twitch -> Observable.just(twitch.name) }.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object: Observer<String>{
//                override fun onComplete() {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onNext(name: String) {
//                    print("RxJava says: $name")
//                }
//
//                override fun onError(e: Throwable) {
//                    e.stackTrace
//                }
//
//            })

        twitchAPI.getTopGamesObservable("stlqknzycm6p4lk8srry5g0feg6dai")
            .flatMap { (game) -> Observable.fromIterable(game) }
            .flatMap { (_, name) -> Observable.just(name) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(name: String) {
                    println("RxJava says: $name")
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {

                }
            })

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
