package com.nomadiq.finnews.presentation.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.invalidateGroupsWithKey
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nomadiq.finnews.domain.model.DogBreed
import com.nomadiq.finnews.domain.model.NewsArticleFeedItem
import com.nomadiq.finnews.presentation.ui.theme.FinNewsShapes


@Preview(name = "ArticleItemShareCTA (light)")
@Preview("ArticleItemShareCTA (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ArticleItemShareCTA(
    modifier: Modifier = Modifier,
    onItemShared: (NewsArticleFeedItem) -> Unit = {},
) {
    Image(
        imageVector = Icons.Filled.Share,
        modifier = modifier
            .size(size = 32.dp)
            .clickable { TODO() /* - look into best place to run onEvent action */ },
        contentDescription = "verified"
    )
}


@Preview(name = "ArticleItemBookmarkCTA (light)")
@Preview("ArticleItemBookmarkCTA (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ArticleItemBookmarkCTA(
    modifier: Modifier = Modifier, onItemBookmarked: (NewsArticleFeedItem) -> Unit = {},
) {
    Image(
        imageVector = Icons.Filled.FavoriteBorder,
        modifier = modifier
            .size(size = 32.dp)
            .clickable { },
        contentDescription = "verified"
    )
}

@Preview(name = "ArticleItemTime (light)")
@Preview("ArticleItemTime (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ArticleItemTime(title: String = "") {
    Text(
        modifier = Modifier.padding(start = 16.dp),
        style = MaterialTheme.typography.labelMedium,
        text = title,
    )
}

@Preview(name = "ArticleItemTitle (light)")
@Preview("ArticleItemTitle (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ArticleItemTitle(
    modifier: Modifier = Modifier,
    title: String = "Article title showing a set number of characters for display.. wrap to second line"
) {
    Text(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        softWrap = true,
        minLines = 1,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyMedium,
        text = title,
    )
}


@Preview(name = "ArticleItemDateStamp (light)")
@Preview("ArticleItemDateStamp (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArticleItemDateStamp(
    modifier: Modifier = Modifier,
    subtitle: String = "TODAY * 2 Min read",
    onItemBookmarked: (NewsArticleFeedItem) -> Unit = {},
    onItemShared: (NewsArticleFeedItem) -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ArticleItemTime(subtitle)
        // CTA Group
        Row(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ArticleItemBookmarkCTA(modifier.padding(end = 8.dp))
            ArticleItemShareCTA()
        }
    }
}


@Preview(name = "ArticleItemDescriptionFooter (light)")
@Preview("ArticleItemDescriptionFooter (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArticleItemDescriptionFooter(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String = "",
    imgUrl: String = "",
    onItemBookmarked: (NewsArticleFeedItem) -> Unit = {},
    onItemShared: (NewsArticleFeedItem) -> Unit = {},
) {
    Column(
    ) {
        ArticleItemTitle(title = title)
        Spacer(modifier = Modifier.padding(8.dp))
        ArticleItemDateStamp(
            subtitle = subtitle,
            onItemShared = onItemShared,
            onItemBookmarked = onItemBookmarked
        )
    }
}

@Preview(name = "ArticleFeedItemImage (light)")
@Preview("ArticleFeedItemImage (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ArticleFeedItemImage(
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
    )
    Spacer(modifier = Modifier.padding(8.dp))
}

@Preview(name = "ArticleFeedItemCard (light)")
@Preview("ArticleFeedItemCard (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArticleFeedItemCard(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String = "",
    imgUrl: String = "",
    item: NewsArticleFeedItem = NewsArticleFeedItem(),
    onItemClick: (NewsArticleFeedItem) -> Unit = {},
    onItemBookmarked: (NewsArticleFeedItem) -> Unit = {},
    onItemShared: (NewsArticleFeedItem) -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(MaterialTheme.shapes.large)
            .clickable(onClick = { onItemClick(item) }),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        ArticleFeedItemImage(
            modifier,
            imgUrl = imgUrl
        )
        Spacer(modifier = Modifier.padding(8.dp))
        ArticleItemDescriptionFooter(
            title = title,
            subtitle = subtitle,
            onItemBookmarked = onItemBookmarked,
            onItemShared = onItemShared
        )
        Spacer(modifier = Modifier.padding(16.dp))
    }
}




