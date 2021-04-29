package com.example.cmtvapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cmtvapp.utils.Constants
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.MOVIES_TABLE)
data class Movie(
    @PrimaryKey
    @NonNull
    val title: String,
    @SerializedName("poster_path") val posterPath: String,
    val overview: String, var isFavourite: Boolean
)