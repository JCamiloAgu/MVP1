package com.example.daggerlogin.http.twitch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Twitch(@SerializedName("data") @Expose var game: List<Game>,
                  @SerializedName("pagination") @Expose private var pagination: Pagination
)

