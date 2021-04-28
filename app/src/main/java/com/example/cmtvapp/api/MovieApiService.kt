package com.example.cmtvapp.api

import com.example.cmtvapp.model.MovieApiResponse
import com.example.cmtvapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET(Constants.POPULAR_MOVIES_ENDPOINT)
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieApiResponse


}