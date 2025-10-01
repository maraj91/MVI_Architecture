package com.example.mvi_architecture.presentaion.moview_list

import com.example.mvi_architecture.domain.model.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null,
    val searchQuery: String = ""
)

