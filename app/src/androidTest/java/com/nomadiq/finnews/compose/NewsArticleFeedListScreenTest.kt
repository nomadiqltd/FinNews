package com.nomadiq.finnews.compose

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nomadiq.finnews.presentation.ui.screens.NewsMainFeedScreen
import com.nomadiq.finnews.presentation.viewmodel.NewsArticleFeedUiState

import org.junit.runner.RunWith

import org.junit.Before
import org.junit.Rule

/**
 *
 *  @author - Michael Akakpo
 *
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NewsArticleFeedListScreenTest {

    private lateinit var navController: TestNavHostController

    companion object {
        private val uiState = NewsArticleFeedUiState(
            items = listOf(),
            isLoading = false,
            errorMessage = "",
        )
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            NewsMainFeedScreen(
                uiState = uiState,
            )
        }
    }
}