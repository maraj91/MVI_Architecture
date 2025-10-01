package com.example.mvi_architecture.presentaion.moview_list

sealed class MovieListIntent {
    object LoadDefaultMovies : MovieListIntent()
    object SearchMovies : MovieListIntent()
    data class UpdateSearchQuery(val query: String) : MovieListIntent()
}
