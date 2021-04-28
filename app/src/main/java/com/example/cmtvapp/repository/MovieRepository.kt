package com.example.cmtvapp.repository

import android.app.Application
import com.example.cmtvapp.api.MovieApiService
import com.example.cmtvapp.api.RetrofitBuilder
import com.example.cmtvapp.model.MovieApiResponse
import com.example.cmtvapp.utils.Constants
import com.example.cmtvapp.utils.UtilMethods

class MovieRepository private constructor(application: Application) {

    private val movieApiService: MovieApiService

    init {

        movieApiService = RetrofitBuilder.movieApiService

    }

    companion object {

        fun getRepoInstance(application: Application): MovieRepository {

            val instance: MovieRepository by lazy { MovieRepository(application) }

            return instance
        }

    }

    suspend fun getPopularMovies() {

        try {
            val response: MovieApiResponse = movieApiService.getPopularMovies(Constants.API_KEY)
            UtilMethods.printI("getPopularMovies request success: $response")
        } catch (exception: Exception) {
            UtilMethods.printI("getPopularMovies request failure: ${exception.message ?: "error occurred"}")
        }

    }

}