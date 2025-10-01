package com.example.mvi_architecture.domain.usecase

data class MovieUseCases(
    val getMovies: GetMoviesUseCase,
    val getMovieDetail: GetMovieDetailUseCase
    // add more later like searchMovies, getPopularMovies etc.
)
