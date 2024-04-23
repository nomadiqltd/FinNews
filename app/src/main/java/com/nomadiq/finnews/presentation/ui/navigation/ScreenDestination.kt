package com.nomadiq.finnews.presentation.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

/**
 *
 * @author - Michael Akakpo
 *
 * Predefined set of values that represent all the possible screens destinations in the app nav graph
 */


/**
 * Contract for information required for every navigation destination
 */
sealed interface ScreenDestination {
    val icon: ImageVector
    val route: String
}


/**
 * News Feed screen
 */
data object NewsArticleFeedListScreen : ScreenDestination {
    override val icon = Icons.Filled.Menu
    override val route = "home_screen"
}

/**
 * News Article Item Detail screen. View a single screen through deeplink (either implicit or explicit)
 */
data object NewsArticleItemDetailScreen : ScreenDestination {
    override val icon = Icons.Filled.Menu
    override val route = "single_article"
    private const val ARTICLE_URL = "id"
    val routeWithArgs = "${route}/{id}"

    val arguments = listOf(
        navArgument(name = ARTICLE_URL) {
            defaultValue = ""
            type = NavType.StringType
        }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "finnews://${route}?=${ARTICLE_URL}" }
    )
}

// Screens to be displayed in the top RallyTabRow
val destinations = listOf(NewsArticleFeedListScreen, NewsArticleItemDetailScreen)