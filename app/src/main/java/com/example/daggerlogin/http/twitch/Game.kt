package com.example.daggerlogin.http.twitch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Game(@SerializedName("id") @Expose val id: String,
                @SerializedName("name") @Expose val name: String,
                @SerializedName("box_art_url") @Expose val boxArtUrl: String)