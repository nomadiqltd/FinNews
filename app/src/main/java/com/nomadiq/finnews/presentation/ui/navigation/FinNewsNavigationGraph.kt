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
import com.nomadiq.finnews.presentation.ui.screens.NewsArticleItemDetailScreen
import com.nomadiq.finnews.presentation.ui.screens.FinNewsMainFeedScreen
import com.nomadiq.finnews.presentation.viewmodel.NewsArticleFeedViewModel
import com.nomadiq.finnews.presentation.viewmodel.NewsArticleItemDetailViewModel
import com.nomadiq.finnews.presentation.viewmodel.sanitiseURL

/**
 *  @author Michael Akakpo
 *
 *  Navigation graph containing Composable and NavHost to describe
 *  relationships between the screens within the app
 *
 */

@ExperimentalAnimationApi
@Composable
fun FinNewsNavigationGraph(
    modifier: Modifier = Modifier,
    startDestination: String = NewsArticleFeedListScreen.route,
) {
    val navController = rememberNavController()

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
                    navController.navigateToArticleItemDetailScreen(article.apiUrl)
                },
                onItemBookmarked = {
                    // TODO() - Check where is best to call Bookmark function
                },
                onItemShared = {
                    // TODO() -Check where is best to call Share function
                }
            )
        }
        composable(
            route = NewsArticleItemDetailScreen.routeWithArgs,
            arguments = NewsArticleItemDetailScreen.arguments,
        ) {
            val newsArticleItemDetailViewModel = hiltViewModel<NewsArticleItemDetailViewModel>()
            val uiState by newsArticleItemDetailViewModel.uiState.collectAsState()
            NewsArticleItemDetailScreen(
                uiState = uiState,
                canNavigateBack = navController.previousBackStackEntry != null,
                onNavigateUp = {
                    navController.navigateUp()
                },
            )
        }
    }
}

fun NavHostController.navigateTo(route: String) =
    this.navigate(route) { launchSingleTop = true }

private fun NavHostController.navigateToArticleItemDetailScreen(apiUrl: String) {
    this.navigateTo(
        route = "${NewsArticleItemDetailScreen.route}/${sanitiseURL(apiUrl)}"
    )
}

