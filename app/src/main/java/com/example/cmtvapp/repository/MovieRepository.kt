package com.example.cmtvapp.repository

import android.app.Application
import com.example.cmtvapp.api.MovieApiService
import com.example.cmtvapp.api.RetrofitBuilder
import com.example.cmtvapp.model.Movie
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

    suspend fun loadMovies(tmdbResultsType: TMDB_RESULTS_TYPE): List<Movie> {

        var moviesList = emptyList<Movie>()

        val response: MovieApiResponse

        try {

            response = when (tmdbResultsType) {

                TMDB_RESULTS_TYPE.POPULAR -> {
                    movieApiService.getPopularMovies(Constants.API_KEY)
                }
                TMDB_RESULTS_TYPE.LATEST -> {
                    movieApiService.getLatestMovies(Constants.API_KEY)
                }

            }

            UtilMethods.printI("success: $response")
            moviesList = response.results

        } catch (exception: Exception) {
            UtilMethods.printE("failure: ${exception.message ?: "error occurred"}")
        }

        return moviesList

    }

    enum class TMDB_RESULTS_TYPE {
        POPULAR,
        LATEST
        //,
        //FAVORITE
    }

}