package com.example.cmtvapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.cmtvapp.model.Movie
import com.example.cmtvapp.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository: MovieRepository

    init {
        movieRepository = MovieRepository.getRepoInstance(application)
    }

    /**remote*/
    fun getPopularMovies(): LiveData<List<Movie>> {
        return liveData<List<Movie>> {
            emit(movieRepository.loadMovies(MovieRepository.TMDB_RESULTS_TYPE.POPULAR))
        }
    }

    fun getLatestMovies(): LiveData<List<Movie>> = liveData {
        emit(movieRepository.loadMovies(MovieRepository.TMDB_RESULTS_TYPE.LATEST))
    }

    /**local*/
    fun getFavoriteMovies(): LiveData<List<Movie>> =
        movieRepository.movieDao.getFavoritesMoviesListLd()

    //----------------------mock-----------------------------------------------

    fun mock() {
        viewModelScope.launch {
            /**mock favourites*/
            val m1 = Movie("title", "/prdCmV8GkDLpguwoxBQczFAwvci.jpg", "good movie")
            val m2 = Movie("title2", "/4qDIb9K9qLxCVT8cKGt5YrF54Xb.jpg", "good movie")
            val m3 = Movie("title3", "/bFPGWSo1FRa5v4M1gJ3Ur95conX.jpg", "good movie")
            val list: List<Movie> = listOf(
                m1,
                m2,
                m3
            )
            val m4 = Movie("alien", "/vfrQk5IPloGg1v9Rzbh2Eg3VGyM.jpg", "good movie")
            //movieRepository.movieDao.insert(m4)
            //insertFavourites(list)
        }
    }

    /**--mock--*/
    suspend fun insertFavourites(list: List<Movie>) {
        movieRepository.movieDao.insertAll(list)
    }

}