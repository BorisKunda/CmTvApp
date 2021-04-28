package com.example.cmtvapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cmtvapp.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("SELECT * from favourites_table")
    fun getFavoritesMoviesListLd(): LiveData<List<Movie>>

    //todo this is mock method - remove
    /**mock*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(moviesList: List<Movie>)

}