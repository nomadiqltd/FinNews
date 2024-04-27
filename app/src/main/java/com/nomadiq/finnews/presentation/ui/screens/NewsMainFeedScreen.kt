package com.nomadiq.finnews.presentation.ui.screens

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nomadiq.finnews.R
import com.nomadiq.finnews.domain.model.NewsArticleFeedItem
import com.nomadiq.finnews.presentation.ui.component.ArticleFeedItemCard
import com.nomadiq.finnews.presentation.ui.component.error.ErrorMessageDisplayView
import com.nomadiq.finnews.presentation.ui.component.NewsTopAppBar
import com.nomadiq.finnews.presentation.ui.theme.FinNewsTheme
import com.nomadiq.finnews.presentation.utils.ErrorType
import com.nomadiq.finnews.presentation.utils.displaySimpleTimeStamp
import com.nomadiq.finnews.presentation.viewmodel.NewsArticleFeedUiState

/**
 *  @author Michael Akakpo
 *
 *  Composable representing the list of [NewsArticleFeedItem] items within the Lazy column
 *
 */

@Preview(name = "FinNewsMainFeedScreen (light)")
@Preview("FinNewsMainFeedScreen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsMainFeedScreen(
    modifier: Modifier = Modifier,
    onItemClick: (NewsArticleFeedItem) -> Unit = {},
    onItemBookmarked: (NewsArticleFeedItem) -> Unit = {},
    onItemShared: (NewsArticleFeedItem) -> Unit = {},
    uiState: NewsArticleFeedUiState = NewsArticleFeedUiState(
        items = listOf(),
    ),
    @StringRes title: Int = R.string.toolbar_title_default
) {
    MainFeedScaffoldState(
        modifier = Modifier,
        title = title,
        uiState = uiState,
        onItemClick = onItemClick,
        onItemBookmarked = onItemBookmarked,
        onItemShared = onItemShared,
    )
}

@Composable
private fun MainFeedScaffoldState(
    modifier: Modifier = Modifier,
    title: Int,
    uiState: NewsArticleFeedUiState,
    onItemClick: (NewsArticleFeedItem) -> Unit = {},
    onItemBookmarked: (NewsArticleFeedItem) -> Unit = {},
    onItemShared: (NewsArticleFeedItem) -> Unit = {},
) {
    FinNewsTheme {
        Scaffold(
            topBar = {
                NewsTopAppBar(
                    title = stringResource(id = title),
                    onAction = {
                        SearchIconButton(onClick = { /* TODO - Run Search via Viewmodel */ })
                    },
                )
            }
        ) { paddingValues ->
            MainScaffoldContentView(
                modifier,
                uiState,
                paddingValues,
                onItemClick,
                onItemBookmarked,
                onItemShared
            )
        }
    }
}

@Composable
private fun MainScaffoldContentView(
    modifier: Modifier = Modifier,
    uiState: NewsArticleFeedUiState = NewsArticleFeedUiState(
        items = listOf(),
    ),
    paddingValues: PaddingValues,
    onItemClick: (NewsArticleFeedItem) -> Unit = {},
    onItemBookmarked: (NewsArticleFeedItem) -> Unit = {},
    onItemShared: (NewsArticleFeedItem) -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding((paddingValues))
    ) {
        // Loading State
        OnLoadingState(uiState)

        // Error State
        OnErrorState(uiState)

        // Article Item Feed
        NewsArticleFeed(uiState.items, onItemClick, onItemBookmarked, onItemShared)
    }
}

@Preview(name = "NewsArticleFeed (light)")
@Preview("NewsArticleFeed (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NewsArticleFeed(
    items: List<NewsArticleFeedItem> = listOf(),
    onItemClick: (NewsArticleFeedItem) -> Unit = {},
    onItemBookmarked: (NewsArticleFeedItem) -> Unit = {},
    onItemShared: (NewsArticleFeedItem) -> Unit = {},
) {
    val state: LazyListState = rememberLazyListState()

    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items.forEach { item ->
            item {
                ArticleFeedItemCard(
                    title = item.title,
                    subtitle = displaySimpleTimeStamp(item.subtitle),
                 //   subtitle = item.subtitle,
                    imgUrl = item.imgUrl,
                    item = item,
                    onItemClick = onItemClick,
                    onItemBookmarked = onItemBookmarked,
                    onItemShared = onItemShared,
                )
            }
        }
    }
}

@Composable
private fun OnLoadingState(uiState: NewsArticleFeedUiState) {
    var loading by rememberSaveable { mutableStateOf(false) }
    loading = uiState.isLoading
    if (loading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
}


@Composable
private fun SearchIconButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.padding(end = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
        )
    }
}

@Composable
private fun OnErrorState(uiState: NewsArticleFeedUiState) {
    when (uiState.errorState) {
        ErrorType.NETWORK_ERROR -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .wrapContentSize()
                    .background(Color.Magenta),
            ) {
                ErrorMessageDisplayView(message = uiState.errorMessage.toString())
            }
        }

        ErrorType.GENERAL_ERROR -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue),
            ) {
                ErrorMessageDisplayView(message = uiState.errorMessage.toString())
            }
        }

        else -> {}
    }
}
