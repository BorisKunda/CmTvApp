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
        insertMockFavourites()
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

}