package com.nomadiq.finnews.presentation.ui.screens

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nomadiq.finnews.R
import com.nomadiq.finnews.domain.model.ArticleFeedItem
import com.nomadiq.finnews.domain.model.DogBreed
import com.nomadiq.finnews.domain.model.NewsChannelFeedItem
import com.nomadiq.finnews.presentation.ui.component.ArticleFeedItemCard
import com.nomadiq.finnews.presentation.ui.component.DefaultSnackBarHost
import com.nomadiq.finnews.presentation.ui.component.DogBreedItemCard
import com.nomadiq.finnews.presentation.ui.component.FeedListHeader
import com.nomadiq.finnews.presentation.ui.component.NewsChannelItemCard
import com.nomadiq.finnews.presentation.ui.component.NewsTopAppBar
import com.nomadiq.finnews.presentation.ui.theme.FinNewsTheme
import com.nomadiq.finnews.presentation.viewmodel.FinNewsFeedListUiState

/**
 *  @author Michael Akakpo
 *
 *  Composable representing the list of [NewsChannelFeedItem] items within the Lazy column
 *
 */

@Preview(name = "FinNewsMainFeedScreen (light)")
@Preview("FinNewsMainFeedScreen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FinNewsMainFeedScreen(
    modifier: Modifier = Modifier,
    onItemClick: (DogBreed) -> Unit = {},
    uiState: FinNewsFeedListUiState = FinNewsFeedListUiState(
        items = listOf(),
        channelItems = listOf(),
        articleItems = listOf()
    ),
    navController: NavHostController = rememberNavController(), // TODO - Refactor into NavController
    @StringRes title: Int = R.string.toolbar_title_default
) {
    MainFeedScaffoldState(
        modifier = Modifier,
        title = title,
        navController = navController,
        uiState = uiState,
        onItemClick = onItemClick,
    )
}

@Composable
private fun MainFeedScaffoldState(
    modifier: Modifier = Modifier,
    title: Int,
    navController: NavHostController,
    uiState: FinNewsFeedListUiState,
    onItemClick: (DogBreed) -> Unit = {},
) {
    FinNewsTheme {
        Scaffold(
            snackbarHost = { DefaultSnackBarHost(hostState = SnackbarHostState()) },
            topBar = {
                NewsTopAppBar(
                    title = title,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
            }
        ) { paddingValues ->
            MainScaffoldContentView(modifier, uiState, paddingValues, onItemClick)
        }
    }
}

@Composable
private fun MainScaffoldContentView(
    modifier: Modifier = Modifier,
    uiState: FinNewsFeedListUiState = FinNewsFeedListUiState(
        items = listOf(),
        channelItems = listOf(),
        articleItems = listOf()
    ),
    paddingValues: PaddingValues,
    onItemClick: (DogBreed) -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding((paddingValues))
    ) {
        // Loading State
        OnLoadingState(uiState)

        // Channel Feed Header
        FeedListHeader()
        // Channel Feed
        NewsChannelFeed(uiState.channelItems)

        Spacer(modifier = Modifier.padding(8.dp))

        // Article Feed Header
        FeedListHeader()
        // Article Feed
        NewsArticleFeeds(uiState.articleItems)

        // Article Feed Header
        FeedListHeader()
        // Article Feed
        NewsArticleFeed(uiState.items, onItemClick)


    }
}


@Preview(name = "NewsChannelFeed (light)")
@Preview("NewsChannelFeed (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NewsChannelFeed(
    channelItems: List<NewsChannelFeedItem> = listOf()
) {
    val state: LazyListState = rememberLazyListState()
    LazyRow(
        state = state,
        modifier = Modifier
            .wrapContentHeight(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        channelItems.forEach { item ->
            item {
                NewsChannelItemCard(
                    modifier = Modifier,
                    name = item.name,
                    isVerified = item.isVerified
                )
            }
        }
    }
}

@Preview(name = "NewsArticleFeed (light)")
@Preview("NewsArticleFeed (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NewsArticleFeed(
    items: List<DogBreed> = listOf(),
    onItemClick: (DogBreed) -> Unit = {}
) {
    val state: LazyListState = rememberLazyListState()
    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items.forEach { item ->
            item {
                DogBreedItemCard(item = item, onItemClick = onItemClick)
            }
        }
    }
}

@Preview(name = "NewsArticleFeeds (light)")
@Preview("NewsArticleFeeds (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NewsArticleFeeds(
    items: List<ArticleFeedItem> = listOf(),
    onItemClick: (ArticleFeedItem) -> Unit = {}
) {
    val state: LazyListState = rememberLazyListState()
    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items.forEach { item ->
            item {
                ArticleFeedItemCard(
                    title = item.title,
                    subtitle = item.subtitle,
                    imgUrl = item.imgUrl
                )
            }
        }
    }
}

@Composable
fun OnLoadingState(uiState: FinNewsFeedListUiState) {
    var loading by rememberSaveable { mutableStateOf(false) }
    loading = uiState.isLoading
    if (loading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            CircularProgressIndicator()
        }
    }
}