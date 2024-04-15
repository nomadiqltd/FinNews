package com.nomadiq.finnews.presentation.ui.component

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nomadiq.finnews.R
import com.nomadiq.finnews.presentation.ui.theme.FinNewsTheme

/**
 * @author Michael Akakpo
 *
 * Composable that displays the topBar and displays back button if back navigation is possible.
 *
 */

@Preview(name = "FinNewsToolbarTitle (light)")
@Preview("FinNewsToolbarTitle (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FinNewsToolbarTitle(
    modifier: Modifier = Modifier,
    @StringRes title: Int = R.string.toolbar_title_default
) {
    Text(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        style = MaterialTheme.typography.titleLarge,
        text = stringResource(id = title),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "FinNewsTopAppBar (light)")
@Preview("FinNewsTopAppBar (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsTopAppBar(
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit = {},
    @StringRes title: Int = R.string.toolbar_title_default
) {
    FinNewsTheme {
        CenterAlignedTopAppBar(
            title = {
                FinNewsToolbarTitle(modifier = modifier, title = title)
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = modifier
                .fillMaxWidth(),
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(title)
                        )
                    }
                }
            }
        )
    }
}

