package com.example.mvi_architecture.domain.usecase

import com.example.mvi_architecture.domain.model.Movie
import com.example.mvi_architecture.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(query: String): List<Movie> =
        repository.getMovies(query)
}

