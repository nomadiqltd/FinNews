package com.nomadiq.finnews.presentation.viewmodel

import com.nomadiq.finnews.domain.model.DogBreed

/**
 *  @author Michael Akakpo
 *
 *  Representing the Read only uiState of the [DogBreedListViewModel], this can be utilised by the relevant @Composable
 *
 */
data class DogBreedListUiState(
    val items: List<DogBreed>,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    /**
     * Converts this [DogBreedListUiState] into a more strongly typed [UiState] for driving
     * the ui.
     */
    fun toUiState() {
        if (items.isNotEmpty()) {
            UiState.Data(items)
        }
    }

}
