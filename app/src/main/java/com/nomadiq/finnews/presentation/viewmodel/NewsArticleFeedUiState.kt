package com.nomadiq.finnews.presentation.viewmodel

import com.nomadiq.finnews.domain.model.ArticleFeedItem

/**
 *  @author Michael Akakpo
 *
 *  Representing the Read only uiState of the [NewsArticleFeedViewModel], this can be utilised by the relevant @Composable
 *
 */
data class NewsArticleFeedUiState(
    val items: List<ArticleFeedItem>,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
}
