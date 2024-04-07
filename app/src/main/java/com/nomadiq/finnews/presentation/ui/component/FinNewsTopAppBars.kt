package com.nomadiq.finnews.presentation.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.nomadiq.finnews.R

/**
 * @author Michael Akakpo
 *
 * Composable that displays the topBar and displays back button if back navigation is possible.
 *
 * Ideally to be used to be used within a specific nest of the navigation graph or "back" action could yield undesirable results.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FinNewsTopAppBar(
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit = {},
    @StringRes title: Int = R.string.toolbar_title_default
) {
    CenterAlignedTopAppBar(
        title = {
            Text(stringResource(id = title))
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier
            .fillMaxWidth(),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.toolbar_title_text)
                    )
                }
            }
        }
    )
}