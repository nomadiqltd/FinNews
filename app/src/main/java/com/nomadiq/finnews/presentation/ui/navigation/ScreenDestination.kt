package com.nomadiq.finnews.presentation.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 *
 * @author - Michael Akakpo
 *
 * Predefined set of values that represent all the possible screens destinations in the app nav graph
 */


/**
 * Contract for route / argument data required for every navigation destination
 */
sealed interface ScreenDestination {
    val route: String
}


/**
 * Main News Article Feed screen
 */
data object NewsArticleFeedListScreen : ScreenDestination {
    override val route = "home_screen"
}

/**
 * News Article Item Detail screen. View a single article item detail screen through deeplink (either implicit or explicit)
 */
data object NewsArticleItemDetailScreen : ScreenDestination {
    override val route = "article"
    private const val ARTICLE_URL = "id"
    val routeWithArgs = "${route}/{${ARTICLE_URL}}"

    val arguments = listOf(
        navArgument(name = ARTICLE_URL) {
            defaultValue = ""
            type = NavType.StringType
        }
    )
}