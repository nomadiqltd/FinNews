package com.nomadiq.finnews.presentation.viewmodel

import com.nomadiq.finnews.domain.model.DogBreed
import com.nomadiq.finnews.domain.model.NewsChannelItem

/**
 *  @author Michael Akakpo
 *
 *  Representing the Read only uiState of the [DogBreedListViewModel], this can be utilised by the relevant @Composable
 *
 */
data class FinNewsFeedListUiState(
    val items: List<DogBreed>,
    val channelItems: List<NewsChannelItem>,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
}
