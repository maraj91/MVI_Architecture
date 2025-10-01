package com.example.mvi_architecture.domain.usecase

import com.example.mvi_architecture.domain.model.Movie
import com.example.mvi_architecture.domain.model.MovieDetail
import com.example.mvi_architecture.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(id: String): MovieDetail {
        return repository.getMovieDetail(id)
    }
}
