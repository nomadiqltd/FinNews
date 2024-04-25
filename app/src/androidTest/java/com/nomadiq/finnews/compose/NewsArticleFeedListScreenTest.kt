package com.nomadiq.finnews.compose

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nomadiq.finnews.presentation.ui.screens.NewsMainFeedScreen
import com.nomadiq.finnews.presentation.viewmodel.NewsArticleFeedUiState
import kotlinx.coroutines.test.runTest

import org.junit.Test
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
            items = listOf(
            ),
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
                onItemClick = {},
                uiState = uiState,
                navController = navController,
            )
        }
    }

    @Test
    fun verify_StartDestination_Is_Main_Dog_breed_ListScreen() = runTest {
        // then
        composeTestRule.onNodeWithText("Dog Breed").assertExists()
        composeTestRule.onNodeWithText("Affenpinscher").assertExists()
        composeTestRule.onNodeWithText("African").assertExists()
        composeTestRule.onNodeWithText("Airedal").assertExists()
        composeTestRule.onNodeWithText("Akita").assertExists()
    }

    @Test
    fun verify_click_on_view_detail_Screen() = runTest {
        // then
        composeTestRule.onNodeWithText("Airedal").assertExists()
            .assertTextContains("Airedal")
            .performClick()
            .assertExists("Detail")
    }
}