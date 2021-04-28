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
    val popularMenuItemClickSld: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val latestMenuItemClickSld: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val favouriteMenuItemClickSld: SingleLiveEvent<Boolean> = SingleLiveEvent()

    init {
        movieRepository = MovieRepository.getRepoInstance(application)
        insertMockFavourites()
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
          movieRepository.movieDao.getFavoritesMoviesListLd().value
    }

    /**mock*/
    fun insertMockFavourites() {

        viewModelScope.launch {

            val m1 = Movie("title", "/prdCmV8GkDLpguwoxBQczFAwvci.jpg", "good movie1")
            val m2 = Movie("title2", "/4qDIb9K9qLxCVT8cKGt5YrF54Xb.jpg", "good movie2")
            val m3 = Movie("title3", "/bFPGWSo1FRa5v4M1gJ3Ur95conX.jpg", "good movie3")
            val mockMovieList: List<Movie> = listOf(
                m1,
                m2,
                m3
            )

            movieRepository.movieDao.insertMockFavouritesAll(mockMovieList)

        }

    }

    fun insertMockFavouriteMovie() {
        viewModelScope.launch {
            val mockMovie = Movie("title4", "/vfrQk5IPloGg1v9Rzbh2Eg3VGyM.jpg", "good movie 4")
            movieRepository.movieDao.insert(mockMovie)
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