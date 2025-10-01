package com.example.mvi_architecture.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mvi_architecture.presentaion.moview_detail.MovieDetailScreen
import com.example.mvi_architecture.presentaion.moview_list.MovieListScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "movie_list"
    ) {
        composable("movie_list") {
            MovieListScreen(
                onMovieClick = { movieId ->
                    navController.navigate("movie_detail/$movieId")
                }
            )
        }

        composable(
            route = "movie_detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            // Uncomment and implement MovieDetailScreen when ready
             MovieDetailScreen(movieId = movieId)
        }
    }
}
