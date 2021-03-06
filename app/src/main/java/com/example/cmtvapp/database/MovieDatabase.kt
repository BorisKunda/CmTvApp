package com.example.cmtvapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cmtvapp.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}