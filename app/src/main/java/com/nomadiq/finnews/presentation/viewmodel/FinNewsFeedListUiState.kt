package com.nomadiq.finnews.presentation.viewmodel

import com.nomadiq.finnews.domain.model.ArticleFeedItem
import com.nomadiq.finnews.domain.model.DogBreed
import com.nomadiq.finnews.domain.model.NewsChannelFeedItem

/**
 *  @author Michael Akakpo
 *
 *  Representing the Read only uiState of the [DogBreedListViewModel], this can be utilised by the relevant @Composable
 *
 */
data class FinNewsFeedListUiState(
    val items: List<DogBreed>,
    val articleItems: List<ArticleFeedItem>,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
}
