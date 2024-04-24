package com.nomadiq.finnews.presentation.ui.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.nomadiq.finnews.R
import com.nomadiq.finnews.domain.model.NewsArticleItemDetail
import com.nomadiq.finnews.presentation.ui.component.NewsTopAppBar
import com.nomadiq.finnews.presentation.ui.theme.FinNewsTheme
import com.nomadiq.finnews.presentation.viewmodel.NewsArticleItemUiState
import com.nomadiq.finnews.presentation.viewmodel.NewsArticleFeedUiState

/**
 * @author - Michael Akakpo
 *
 * The detail screen which displays an [NewsArticleItemDetail]
 */


@Composable
fun NewsArticleItemDetailScreen(
    uiState: NewsArticleItemUiState,
    canNavigateBack: Boolean = false,
    onNavigateUp: () -> Unit = {},
    @StringRes title: Int = R.string.toolbar_title_default
) {
    FinNewsTheme {
        Scaffold(
            topBar = {
                NewsTopAppBar(
                    title = title,
                    canNavigateBack = canNavigateBack,
                    navigateUp = { onNavigateUp() }
                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                content = {
                    ArticleItemDetailImage(imgUrl = uiState.item.imgUrl)
                    Spacer(modifier = Modifier.padding(8.dp))
                    ArticleItemDetailTitle(title = uiState.item.title)
                    Spacer(modifier = Modifier.padding(8.dp))
                    ArticleItemDetailBody(title = uiState.item.body)
                }
            )
        }
    }
}

@Preview(name = "ArticleItemTitle (light)")
@Preview("ArticleItemTitle (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArticleItemDetailTitle(modifier: Modifier = Modifier, title: String = "Sky News") {
    Text(
        modifier = Modifier.padding(start = 16.dp, end = 0.dp, bottom = 8.dp),
        style = MaterialTheme.typography.titleLarge,
        text = title,
    )
}

@Preview(name = "ArticleItemDetailBody (light)")
@Preview("ArticleItemDetailBody (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArticleItemDetailBody(modifier: Modifier = Modifier, title: String = "Loreum ipsum") {
    Text(
        modifier = Modifier.padding(start = 16.dp, end = 0.dp, bottom = 8.dp),
        style = MaterialTheme.typography.bodyMedium,
        text = title,
    )
}


@Preview(name = "ArticleItemDetailImage (light)")
@Preview("ArticleItemDetailImage (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ArticleItemDetailImage(
    modifier: Modifier = Modifier,
    imgUrl: String = "https://source.unsplash.com/random/500x500?sig=1"
) {
    Image(
        painter = rememberAsyncImagePainter(
            model = imgUrl,
        ),
        contentDescription = "article image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(ratio = 3f / 2f, matchHeightConstraintsFirst = true)
            .clip(MaterialTheme.shapes.large),
    )
    Spacer(modifier = Modifier.padding(8.dp))
}

@Composable
fun OnLoadingState(uiState: NewsArticleItemUiState) {
    var loading by rememberSaveable { mutableStateOf(false) }
    loading = uiState.isLoading
    if (loading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow),
        ) {
            CircularProgressIndicator()
        }
    }
}