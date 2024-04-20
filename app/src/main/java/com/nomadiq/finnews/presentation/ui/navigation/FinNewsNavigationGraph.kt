package com.nomadiq.finnews.presentation.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nomadiq.finnews.presentation.ui.screens.DogBreedItemDetailScreen
import com.nomadiq.finnews.presentation.ui.screens.FinNewsMainFeedScreen
import com.nomadiq.finnews.presentation.viewmodel.NewsArticleFeedViewModel
import com.nomadiq.finnews.presentation.viewmodel.DogBreedRandomImageViewModel

/**
 *  @author Michael Akakpo
 *
 *  Navigation graph containing Composable and NavHost to describe
 *  relationships between the screens within the app
 *
 */

@ExperimentalAnimationApi
@Composable
fun FinNewsGraph(
    modifier: Modifier = Modifier,
    startDestination: String = ScreenDestination.DogBreedListScreen.route,
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        composable(
            route = ScreenDestination.DogBreedListScreen.route
        ) {
            val viewModel = hiltViewModel<NewsArticleFeedViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            FinNewsMainFeedScreen(
                uiState = uiState,
                navController = navController,
                onItemClick = {
                    navController.navigate(
                        ScreenDestination.DogBreedDetailScreen.createRoute(
                            breed = it.name.lowercase()
                        )
                    )
                }
            )
        }
        composable(
            route = ScreenDestination.DogBreedDetailScreen.route,
            arguments = ScreenDestination.DogBreedDetailScreen.navArguments
        ) {
            val dogBreedRandomImageViewModel = hiltViewModel<DogBreedRandomImageViewModel>()
            val uiState by dogBreedRandomImageViewModel.uiState.collectAsState()
            DogBreedItemDetailScreen(
                navController = navController,
                uiState = uiState
            )
        }
    }
}