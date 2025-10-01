package com.example.mvi_architecture.presentaion.moview_detail

sealed class MovieDetailIntent {
    data class LoadMovieDetail(val movieId: String) : MovieDetailIntent()
}
