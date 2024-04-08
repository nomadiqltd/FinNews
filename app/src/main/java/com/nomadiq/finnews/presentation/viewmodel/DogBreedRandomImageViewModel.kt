package com.nomadiq.finnews.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomadiq.finnews.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.finnews.domain.model.DogBreed
import com.nomadiq.finnews.domain.model.DogBreedImageDetail
import com.nomadiq.finnews.domain.usecase.GetDogBreedRandomImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author - Michael Akakpo
 *
 * [DogBreedRandomImageViewModel]
 *
 * Defines UI State information about the details of the items of the list of [DogBreed].
 * This uiState displays the list of random images retrieved from a particular [DogBreed]
 *
 */

@HiltViewModel
class DogBreedRandomImageViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getDogBreedRandomImageUseCase: GetDogBreedRandomImageUseCase
) : ViewModel() {

    // Define UIState variable for [DogBreed] data to map to the equivalent UI Screens and components
    private val _uiState = MutableStateFlow(DogBreedRandomImageUiState(listOf()))
    val uiState: StateFlow<DogBreedRandomImageUiState> = _uiState.asStateFlow()

    // TODO() - constant value // TODO: store key
    val breed: String =
        checkNotNull(savedStateHandle.getStateFlow(key = "breed", initialValue = "")).value

    init {
        displayDogBreedRandomImageList(breed.lowercase())
    }

    // Function to fetch List of [DogBreedImageDetail] - save success response and update uiState
    private fun displayDogBreedRandomImageList(breed: String) {
        viewModelScope.launch {
            getDogBreedRandomImageUseCase.invoke(breed).collect { result ->
                Log.d("displayDogBreedRandomImageList ==> ", breed)
                when (result) {
                    is DogBreedRandomImageResult.Data -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = true,
                                items =
                                result.items.map {
                                    DogBreedImageDetail(
                                        imageUrl = it.imageUrl
                                    )
                                },
                            )
                        }
                        Log.d(
                            "displayDogBreedRandomImageList() ==> ",
                            "${result.items.size}"
                        )
                    }

                    else -> {
                        _uiState.update { currentState -> currentState.copy(isLoading = false) }
                    }
                }
            }
        }
        _uiState.update { currentState -> currentState.copy(isLoading = false) }
    }
}
