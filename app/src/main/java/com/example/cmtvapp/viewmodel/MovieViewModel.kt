package com.example.cmtvapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cmtvapp.model.Movie
import com.example.cmtvapp.repository.MovieRepository
import com.example.cmtvapp.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository: MovieRepository
    var moviesListMld: MutableLiveData<List<Movie>> = MutableLiveData()
    var moviesListLd: LiveData<List<Movie>> = moviesListMld
    val selectedMovieMld: MutableLiveData<Movie> = MutableLiveData()
    val selectedMovieLd: LiveData<Movie> = selectedMovieMld
    val popularMenuItemClickSld: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val latestMenuItemClickSld: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val favouriteMenuItemClickSld: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val openMovieDetailsScreenSld: SingleLiveEvent<Boolean> = SingleLiveEvent()

    init {
        movieRepository = MovieRepository.getRepoInstance(application)
    }

    /**remote*/

    fun loadPopularMovies() {

        viewModelScope.launch {
            moviesListMld.value =
                movieRepository.getMovies(MovieRepository.TMDB_RESULTS_TYPE.POPULAR)
        }

    }

    fun loadLatestMovies() {

        viewModelScope.launch {
            moviesListMld.value =
                movieRepository.getMovies(MovieRepository.TMDB_RESULTS_TYPE.LATEST)
        }

    }

    /**local*/

    fun loadFavouriteMovies() {

        viewModelScope.launch {
            moviesListMld.value = movieRepository.movieDao.getFavoritesMoviesList()
        }

    }

    fun onPopularMenuItemClicked() {
        popularMenuItemClickSld.call()
    }

    fun onLatestMenuItemClicked() {
        latestMenuItemClickSld.call()
    }

    fun onFavouritesMenuItemClicked() {
        favouriteMenuItemClickSld.call()
    }

}