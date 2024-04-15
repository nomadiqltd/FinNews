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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nomadiq.finnews.presentation.ui.theme.FinNewsShapes


@Preview(name = "ArticleCTAShare (light)")
@Preview("ArticleCTAShare (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ArticleCTAShare(modifier: Modifier = Modifier) {
    Image(
        imageVector = Icons.Filled.Share,
        modifier = modifier
            .size(size = 20.dp)
            .background(color = Color.Green, shape = RectangleShape)
            .border(
                border = BorderStroke(1.dp, Color.Black),
            ),
        contentDescription = "verified"
    )
}


@Preview(name = "ArticleCTABookmark (light)")
@Preview("ArticleCTABookmark (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ArticleCTABookmark(modifier: Modifier = Modifier) {
    Image(
        imageVector = Icons.Filled.FavoriteBorder,
        modifier = modifier
            .size(size = 20.dp)
            .background(color = Color.Green, shape = CircleShape)
            .border(
                border = BorderStroke(1.dp, Color.Black),
                shape = CircleShape
            ),
        contentDescription = "verified"
    )
}

@Preview(name = "ArticleItemTime (light)")
@Preview("ArticleItemTime (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ArticleItemTime(title: String = "") {
    Text(
        modifier = Modifier,
        style = MaterialTheme.typography.labelSmall,
        text = title,
    )
}

@Preview(name = "ArticleItemTitle (light)")
@Preview("ArticleItemTitle (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ArticleItemTitle(
    modifier: Modifier = Modifier,
    title: String = "Article title showing a \n set number of characters for display...\n wrap to second line"
) {
    Text(
        modifier = Modifier,
        softWrap = true,
        minLines = 1,
        maxLines = 8,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.labelMedium,
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
            ArticleCTABookmark(modifier.padding(end = 8.dp))
            ArticleCTAShare()
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
        Spacer(modifier = Modifier.padding(8.dp))
        ArticleItemDateStamp()
    }
}

@Preview(name = "ArticleItemCard (light)")
@Preview("ArticleItemCard (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArticleFeedItemCard(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String = "",
    imgUrl: String = ""
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(
                border = BorderStroke(1.dp, Color.Black),
                shape = FinNewsShapes.large
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ArticleFeedItemImage(modifier, imgUrl)
        Spacer(modifier = Modifier.padding(8.dp))
        ArticleItemDescriptionFooter()
    }
}

@Composable
private fun ArticleFeedItemImage(
    modifier: Modifier = Modifier,
    imgUrl: String = "https://media.guim.co.uk/b4b8ba7d544a93d10982353d581717bfdf7888ee/1_472_5303_3183/500.jpg"
) {
    AsyncImage(
        model = imgUrl,
        modifier = modifier
            .wrapContentSize()
            .clip(FinNewsShapes.large)
            .border(
                border = BorderStroke(1.dp, Color.Black),
                shape = FinNewsShapes.large
            ),
        contentDescription = "verified"
    )
}

@Preview(name = "ArticleFeedItemHorizontalCard (light)")
@Preview("ArticleFeedItemHorizontalCard (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArticleFeedItemHorizontalCard(
    modifier: Modifier = Modifier,
    title: String = "sdsds",
    subtitle: String = "",
    imgUrl: String = "https://media.guim.co.uk/b4b8ba7d544a93d10982353d581717bfdf7888ee/1_472_5303_3183/500.jpg"
) {
    Column {
        Row(
            modifier
                .wrapContentSize()
                .border(
                    border = BorderStroke(1.dp, Color.Black),
                    shape = FinNewsShapes.large
                ),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ArticleItemTitle()
            ArticleFeedItemImage(
                modifier
                    .weight(1f)
                    .size(150.dp), imgUrl = imgUrl
            )
        }

        Spacer(modifier = Modifier.padding(4.dp))

        Column(
            modifier = Modifier
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            ArticleItemDescriptionFooter()
        }
    }
}







