package com.example.mvi_architecture.presentaion.moview_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_architecture.domain.usecase.GetMoviesUseCase
import com.example.mvi_architecture.domain.usecase.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val useCases: MovieUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(MovieListState())
    val state: StateFlow<MovieListState> = _state.asStateFlow()

    fun handleIntent(intent: MovieListIntent) {
        when (intent) {
            is MovieListIntent.LoadDefaultMovies -> loadMovies("batman")
            is MovieListIntent.SearchMovies -> loadMovies(state.value.searchQuery)
            is MovieListIntent.UpdateSearchQuery -> 
                _state.update { it.copy(searchQuery = intent.query) }
        }
    }

    private fun loadMovies(query: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            runCatching { useCases.getMovies(query) }
                .onSuccess { movies ->
                    _state.update { it.copy(isLoading = false, movies = movies) }
                }
                .onFailure { e ->
                    _state.update { it.copy(isLoading = false, error = e.message) }
                }
        }
    }
}
