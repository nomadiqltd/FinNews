package com.nomadiq.finnews.screens

import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nomadiq.finnews.presentation.ui.screens.NewsMainFeedScreen
import com.nomadiq.finnews.presentation.utils.ErrorType
import com.nomadiq.finnews.presentation.utils.displaySimpleTimeStamp
import com.nomadiq.finnews.presentation.viewmodel.NewsArticleFeedUiState
import com.nomadiq.finnews.utils.TestConstants
import com.nomadiq.finnews.utils.listOfArticles
import com.nomadiq.finnews.utils.listOfTestCards
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NewsArticleFeedMainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    companion object {
        private val uiState = NewsArticleFeedUiState(
            items = listOfArticles,
            isLoading = true,
            errorMessage = ErrorType.GENERAL_ERROR.name,
        )
    }

    /** Loading states */
    @Test
    fun `initialize and check loading state is correct`() {
        composeTestRule.setContent {
            NewsMainFeedScreen(
                uiState = NewsArticleFeedUiState(listOf(), isLoading = true)
            )
        }

        composeTestRule.onNodeWithTag("CircularProgressIndicator").assertIsDisplayed()
    }


    @Test
    fun `initialize and check network error state`() {
        composeTestRule.setContent {
            NewsMainFeedScreen(
                uiState = NewsArticleFeedUiState(
                    listOf(),
                    errorMessage = TestConstants.NETWORK_ERROR_MESSAGE,
                    errorState = ErrorType.NETWORK_ERROR
                )
            )
        }

        composeTestRule.onNodeWithText(TestConstants.NETWORK_ERROR_MESSAGE).assertIsDisplayed()
    }


    @Test
    fun `initialize and check general error state`() {
        composeTestRule.setContent {
            NewsMainFeedScreen(
                uiState = NewsArticleFeedUiState(
                    listOf(),
                    errorMessage = TestConstants.UNKNOWN_ERROR,
                    errorState = ErrorType.GENERAL_ERROR
                )
            )
        }

        composeTestRule.onNodeWithText(TestConstants.UNKNOWN_ERROR).assertIsDisplayed()
    }

    @Test
    fun `initialize and check success loading data state`() {
        composeTestRule.setContent {
            NewsMainFeedScreen(
                uiState = NewsArticleFeedUiState(
                    listOfArticles,
                )
            )
        }

        // App bar is displayed
        composeTestRule.onNodeWithTag("NewsTopAppBarTag").assertIsDisplayed()
        // List of items in Cards
        listOfArticles.forEach { article ->
            composeTestRule.onNodeWithText(article.title).assertIsDisplayed()
            //  composeTestRule.onNodeWithText(displaySimpleTimeStamp(article.subtitle)).assertTextContains(article.subtitle)
            //  composeTestRule.onNodeWithContentDescription(article.title).assertContentDescriptionEquals(article.title)
        }
    }
}