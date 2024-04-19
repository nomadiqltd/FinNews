package com.nomadiq.finnews.presentation.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter


@Preview(name = "ArticleItemShareCTA (light)")
@Preview("ArticleItemShareCTA (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ArticleItemShareCTA(modifier: Modifier = Modifier) {
    Image(
        imageVector = Icons.Filled.Share,
        modifier = modifier
            .size(size = 32.dp),
        contentDescription = "verified"
    )
}


@Preview(name = "ArticleItemBookmarkCTA (light)")
@Preview("ArticleItemBookmarkCTA (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ArticleItemBookmarkCTA(modifier: Modifier = Modifier) {
    Image(
        imageVector = Icons.Filled.FavoriteBorder,
        modifier = modifier
            .size(size = 32.dp),
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
        style = MaterialTheme.typography.labelLarge,
        text = title,
    )
}


@Preview(name = "ArticleItemDateStamp (light)")
@Preview("ArticleItemDateStamp (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArticleItemDateStamp(modifier: Modifier = Modifier, subtitle: String = "TODAY * 2 Min read") {
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
fun ArticleItemDescriptionFooter(modifier: Modifier = Modifier, title: String = "") {
    Column(
    ) {
        ArticleItemTitle()
        Spacer(modifier = Modifier.padding(8.dp))
        ArticleItemDateStamp()
    }
}


@Composable
private fun ArticleFeedItemImage(
    modifier: Modifier = Modifier,
    imgUrl: String = "https://source.unsplash.com/random/400x200?sig=1"
) {
    Image(
        painter = rememberAsyncImagePainter(
            model = imgUrl,
        ),
        contentDescription = "article image ",
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .aspectRatio(ratio = 2f / 2f)
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
    imgUrl: String = ""
) {
    Card(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        ArticleFeedItemImage(
            modifier,
            imgUrl = "https://source.unsplash.com/random/200x200?sig=1"
        )
        Spacer(modifier = Modifier.padding(8.dp))
        ArticleItemDescriptionFooter()
        Spacer(modifier = Modifier.padding(16.dp))
    }
}




