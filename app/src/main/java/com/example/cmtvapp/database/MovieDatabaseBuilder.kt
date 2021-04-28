package com.example.cmtvapp.database

import android.app.Application
import androidx.room.Room
import com.example.cmtvapp.utils.Constants

object MovieDatabaseBuilder {

    fun getMovieDatabase(application: Application): MovieDatabase {

        val movieDatabase: MovieDatabase by lazy {
            Room.databaseBuilder(application, MovieDatabase::class.java, Constants.DATABASE_NAME)
                .build()
        }
        return movieDatabase

    }

}