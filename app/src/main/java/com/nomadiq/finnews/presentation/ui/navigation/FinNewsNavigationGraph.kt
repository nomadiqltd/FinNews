package com.nomadiq.finnews.presentation.ui.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
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
import com.nomadiq.finnews.presentation.ui.screens.NewsArticleItemDetailScreen
import com.nomadiq.finnews.presentation.ui.screens.FinNewsMainFeedScreen
import com.nomadiq.finnews.presentation.viewmodel.NewsArticleFeedViewModel
import com.nomadiq.finnews.presentation.viewmodel.NewsArticleItemDetailViewModel
import com.nomadiq.finnews.presentation.viewmodel.encodeURL
import kotlin.io.encoding.ExperimentalEncodingApi

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
    startDestination: String = NewsArticleFeedListScreen.route,
) {
    //var currentScreen: RallyDestination by remember { mutableStateOf(Overview) }
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    // Fetch your currentDestination:
    val currentDestination = currentBackStack?.destination

    // Change the variable to this and use Overview as a backup screen if this returns null
    val currentScreen =
        destinations.find { it.route == currentDestination?.route } ?: NewsArticleFeedListScreen

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(
            route = NewsArticleFeedListScreen.route,
        ) {
            val viewModel = hiltViewModel<NewsArticleFeedViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            FinNewsMainFeedScreen(
                uiState = uiState,
                onItemClick = { article ->
                    navController.navigateToArticleItemDetail(article.apiUrl)
                }
            )
        }
        composable(
            route = NewsArticleItemDetailScreen.routeWithArgs,
            arguments = NewsArticleItemDetailScreen.arguments,
            //  deepLinks = NewsArticleItemDetailScreen.deepLinks
        ) {
            val newsArticleItemDetailViewModel = hiltViewModel<NewsArticleItemDetailViewModel>()
            val uiState by newsArticleItemDetailViewModel.uiState.collectAsState()
            NewsArticleItemDetailScreen(
                uiState = uiState,
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }

@OptIn(ExperimentalEncodingApi::class)
private fun NavHostController.navigateToArticleItemDetail(apiUrl: String) {
    this.navigateSingleTopTo(
        route = "${NewsArticleItemDetailScreen.route}/${encodeURL(apiUrl)}"
    )
}

