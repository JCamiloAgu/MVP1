package com.example.daggerlogin.http.twitch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pagination (@SerializedName("cursor") @Expose private val cursor: String)
