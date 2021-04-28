package com.example.cmtvapp.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val title: String,
    @SerializedName("poster_path") val posterPath: String,
    val overview: String
)