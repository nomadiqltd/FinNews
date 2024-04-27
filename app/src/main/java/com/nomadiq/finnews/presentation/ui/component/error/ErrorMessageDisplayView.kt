package com.nomadiq.finnews.presentation.ui.component.error

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(name = "ErrorMessageDisplay (light)")
@Preview("ErrorMessageDisplay (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ErrorMessageDisplayView(
    modifier: Modifier = Modifier,
    message: String = ""
) {
    Text(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        softWrap = true,
        minLines = 1,
        maxLines = 4,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyLarge,
        text = message,
    )
}