package com.example.mvi_architecture.presentaion.moview_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun MovieDetailScreen(
    movieId: String,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.handleIntent(MovieDetailIntent.LoadMovieDetail(movieId))
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (state.error != null) {
        Text("Error: ${state.error}", modifier = Modifier.padding(16.dp))
    } else {
        state.movie?.let { movie ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp)) {
                AsyncImage(
                    model = movie.poster,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Spacer(Modifier.height(16.dp))
                Text(movie.title, style = MaterialTheme.typography.titleLarge)
                Text("${movie.genre} | ${movie.runtime} | ${movie.rated}", style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
                Text("Director: ${movie.director}", style = MaterialTheme.typography.bodyMedium)
                Text("Actors: ${movie.actors}", style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
                Text(movie.plot, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
                movie.ratings.forEach { rating ->
                    Text("${rating.source}: ${rating.value}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

