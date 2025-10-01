package com.example.mvi_architecture.data.remote

import com.example.mvi_architecture.data.model.movie.MovieSearchResponse
import com.example.mvi_architecture.data.model.movie_details.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET(".")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") query: String
    ): MovieSearchResponse

    @GET("/")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String
    ): MovieDetailResponse
}
