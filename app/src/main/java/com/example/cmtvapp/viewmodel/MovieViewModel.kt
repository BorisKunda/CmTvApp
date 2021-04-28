package com.example.cmtvapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmtvapp.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val scopeVm: CoroutineScope
    private val movieRepository: MovieRepository

    init {

        scopeVm = viewModelScope
        movieRepository = MovieRepository.getRepoInstance(application)

    }

    fun loadPopularMovies() {

        scopeVm.launch {
            movieRepository.getPopularMovies()
        }

    }

}