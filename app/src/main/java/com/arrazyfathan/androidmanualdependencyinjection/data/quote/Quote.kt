package com.arrazyfathan.androidmanualdependencyinjection.data.quote

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("_id")
    val id: String,
    val author: String,
    val content: String
)