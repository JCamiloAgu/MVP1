package com.example.daggerlogin.http

import com.example.daggerlogin.http.twitch.Twitch
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TwitchAPI {
    @GET("games/top")
    fun getTopGames(@Header("Client-id") clientId: String): Call<Twitch>

    @GET("games/top")
    fun getTopGamesObservable(@Header("Client-id") clientId: String): Observable<Twitch>
}