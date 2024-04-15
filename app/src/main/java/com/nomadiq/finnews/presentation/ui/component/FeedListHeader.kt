package com.nomadiq.finnews.presentation.ui.component

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nomadiq.finnews.R

/**
 * @author Michael Akakpo
 *
 * Composable to represent FeedItems
 */

@Preview(name = "FeedListTitle (light)")
@Preview("FeedListTitle (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FeedListTitle(
    modifier: Modifier = Modifier, @StringRes title: Int = R.string.feed_header_title
) {
    Text(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        style = typography.titleMedium,
        text = stringResource(id = title),
    )
}


@Preview(name = "FeedListCTATitle (light)")
@Preview("FeedListCTATitle (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FeedListCTAName(modifier: Modifier = Modifier, title: String = "View all") {
    Text(
        modifier = Modifier,
        style = MaterialTheme.typography.labelSmall,
        text = title,
    )
}

@Preview(name = "FeedListCTA (light)")
@Preview("FeedListCTA (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FeedListCTA(modifier: Modifier = Modifier, text: String = "View all") {
    Button(
        modifier = modifier
            .padding()
            .height(30.dp),
        onClick = { },
        content = { FeedListCTAName(title = text) },
    )
}


@Preview(name = "FeedListHeader (light)")
@Preview("FeedListHeader (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FeedListHeader(
    modifier: Modifier = Modifier,
    title: String = "",
    cta: String = ""
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 12.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FeedListTitle()
        FeedListCTA()
    }
}