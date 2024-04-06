package com.nomadiq.finnews.presentation.viewmodel

import com.nomadiq.finnews.domain.model.DogBreedImageDetail

/**
 *  @author Michael Akakpo
 *
 *  Representing the uiState of the [DogBreedRandomImageViewModel], this can be utilised the relevant @Composable
 *
 */
data class DogBreedRandomImageUiState(
    val items: List<DogBreedImageDetail>,
    val isLoading: Boolean = true
)
