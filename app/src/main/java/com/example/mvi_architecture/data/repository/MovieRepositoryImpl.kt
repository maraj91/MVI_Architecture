package com.example.mvi_architecture.data.repository

import com.example.mvi_architecture.BuildConfig
import com.example.mvi_architecture.data.model.movie.toDomain
import com.example.mvi_architecture.data.model.movie_details.toDomain
import com.example.mvi_architecture.data.remote.MovieApi
import com.example.mvi_architecture.domain.model.Movie
import com.example.mvi_architecture.domain.model.MovieDetail
import com.example.mvi_architecture.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    private val apiKey = BuildConfig.OMDB_API_KEY // Replace with OMDb key

    override suspend fun getMovies(query: String): List<Movie> {
        val response = api.searchMovies(apiKey, query)
        return response.search?.map { it.toDomain() } ?: emptyList()
    }

    override suspend fun getMovieDetail(id: String): MovieDetail {
        val response = api.getMovieDetail(id, apiKey)
        return response.toDomain()
    }
}
