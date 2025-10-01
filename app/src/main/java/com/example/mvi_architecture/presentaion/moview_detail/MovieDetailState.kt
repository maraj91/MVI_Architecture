package com.example.mvi_architecture.presentaion.moview_detail

import com.example.mvi_architecture.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String? = null
)

