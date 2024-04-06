package com.nomadiq.chipdogstask.presentation.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nomadiq.chipdogstask.presentation.ui.component.DefaultSnackBarHost
import com.nomadiq.chipdogstask.presentation.ui.navigation.ScreenDestination.DogBreedDetailScreen.destinations
import com.nomadiq.chipdogstask.presentation.ui.screens.DogBreedItemDetailScreen
import com.nomadiq.chipdogstask.presentation.ui.screens.DogBreedListScreen
import com.nomadiq.chipdogstask.presentation.viewmodel.DogBreedListViewModel
import com.nomadiq.chipdogstask.presentation.viewmodel.DogBreedRandomImageViewModel

/**
 *  @author Michael Akakpo
 *
 *  Navigation graph containing Composable and NavHost to demonstrate
 *  relationships between screens
 *
 */

@ExperimentalAnimationApi
@Composable
fun DogBreedsApp() {
    val navController: NavHostController = rememberNavController()

    val currentBackStack by navController.currentBackStackEntryAsState()
    // Fetch your currentDestination:
    val currentDestination = currentBackStack?.destination

    // Change the variable to this and use Overview as a backup screen if this returns null
//    val currentScreen =
  //      destinations.find { it.route == currentDestination?.route } ?: ScreenDestination.DogBreedListScreen

    Scaffold(

    ) { paddingValues ->
        NavHost(
            navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            startDestination = ScreenDestination.DogBreedListScreen.route
        ) {
            composable(
                route = ScreenDestination.DogBreedListScreen.route
            ) {
                val viewModel = hiltViewModel<DogBreedListViewModel>()
                val uiState by viewModel.uiState.collectAsState()
                DogBreedListScreen(
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
}