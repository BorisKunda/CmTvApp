package com.example.cmtvapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.cmtvapp.model.Movie
import com.example.cmtvapp.repository.MovieRepository

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository: MovieRepository

    init {

        movieRepository = MovieRepository.getRepoInstance(application)

    }

    /**remote*/

    fun getPopularMovies(): LiveData<List<Movie>> = liveData {
        emit(movieRepository.loadMovies(MovieRepository.TMDB_RESULTS_TYPE.POPULAR))
    }

    fun getLatestMovies(): LiveData<List<Movie>> = liveData {
        emit(movieRepository.loadMovies(MovieRepository.TMDB_RESULTS_TYPE.LATEST))
    }

}