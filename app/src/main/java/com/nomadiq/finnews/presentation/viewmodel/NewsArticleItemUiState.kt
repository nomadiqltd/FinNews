package com.nomadiq.finnews.presentation.viewmodel

import com.nomadiq.finnews.domain.model.NewsArticleItemDetail
import com.nomadiq.finnews.presentation.utils.ErrorType

/**
 *  @author Michael Akakpo
 *
 *  Representing the uiState of the [NewsArticleItemDetail], this can be utilised in the relevant @Composable
 *
 */
data class NewsArticleItemUiState(
    val item: NewsArticleItemDetail = NewsArticleItemDetail(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val errorState: ErrorType? = null,
)
