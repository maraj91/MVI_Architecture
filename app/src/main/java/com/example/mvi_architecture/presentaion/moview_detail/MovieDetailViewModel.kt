package com.example.mvi_architecture.presentaion.moview_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_architecture.domain.usecase.GetMovieDetailUseCase
import com.example.mvi_architecture.domain.usecase.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val useCases: MovieUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailState())
    val state: StateFlow<MovieDetailState> = _state.asStateFlow()

    fun handleIntent(intent: MovieDetailIntent) {
        when (intent) {
            is MovieDetailIntent.LoadMovieDetail -> fetchMovieDetail(intent.movieId)
        }
    }

    private fun fetchMovieDetail(movieId: String) {
        viewModelScope.launch {
            _state.value = MovieDetailState(isLoading = true)
            try {
                val movie = useCases.getMovieDetail(movieId) // ✅ use the use case
                _state.value = MovieDetailState(movie = movie)
            } catch (e: Exception) {
                _state.value = MovieDetailState(error = e.message ?: "Unknown error")
            }
        }
    }
}


