package com.nomadiq.finnews.presentation.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nomadiq.finnews.presentation.ui.theme.FinNewsShapes

/**
 * @author Michael Akakpo
 *
 * Composable to represent each [FinNewsChannelItem] item in the list retrieved from the [API].
 */

@Preview(name = "TopAppbar (light)")
@Preview("TopAppbar (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChannelItemCTA(modifier: Modifier = Modifier, text: String = "Follow") {
    Button(
        modifier = modifier,
        onClick = { },
        content = { ChannelItemTitle(title = text) },
    )
}


@Preview(name = "TopAppbar (light)")
@Preview("TopAppbar (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChannelItemTitle(modifier: Modifier = Modifier, title: String = "Sky News") {
    Text(
        modifier = Modifier.padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 8.dp),
        style = MaterialTheme.typography.labelSmall,
        text = title,
    )
}

@Preview(name = "TopAppbar (light)")
@Preview("TopAppbar (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChannelItemIconVerified(modifier: Modifier = Modifier) {
    Image(
        imageVector = Icons.Outlined.Check,
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


@Preview(name = "TopAppbar (light)")
@Preview("TopAppbar (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChannelItemIcon(modifier: Modifier = Modifier, isVerified: Boolean = false) {
    Box(modifier = modifier)
    {
        Image(
            imageVector = Icons.Filled.Lock,
            modifier = modifier
                .size(size = 48.dp)
                .align(Alignment.Center)
                .background(color = Color.Red, shape = CircleShape),
            contentDescription = "Channel Icon"
        )
        if (isVerified)
            ChannelItemIconVerified(
                modifier = modifier
                    .align(Alignment.BottomEnd)
            )
    }
}

@Preview(name = "TopAppbar (light)")
@Preview("TopAppbar (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FinNewsChannelItem(
    modifier: Modifier = Modifier,
    name: String = "channel_title",
    isVerified: Boolean = false,
) {
    Column(
        modifier = modifier
            .wrapContentWidth()
            .border(
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                shape = FinNewsShapes.large
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        ChannelItemIcon(modifier = Modifier.padding(4.dp), isVerified = isVerified)
        ChannelItemTitle(
            modifier = modifier, title = name
        )
    }
}

