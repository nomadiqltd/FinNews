package com.nomadiq.chipdogstask.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomadiq.chipdogstask.domain.usecase.GetDogBreedListUseCase
import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.domain.model.DogBreed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject

/**
 * @author - Michael Akakpo
 *
 * [DogBreedListViewModel]
 *
 * Defines UI State information about the list of [DogBreed]
 * retrieved from the API and make it accessible to Compose and other UI components
 *
 */

@HiltViewModel
class DogBreedListViewModel @Inject constructor(
    private val getDogBreedListUseCase: GetDogBreedListUseCase
) :
    ViewModel() {

    // Define UIState variable for [DogBreed] data to map to the equivalent UI Screens and components
    private val _uiState = MutableStateFlow(DogBreedListUiState(listOf()))
    val uiState: StateFlow<DogBreedListUiState> = _uiState.asStateFlow()

    init {
        displayDogBreedList()
    }

    // Function to fetch List of [DogBreed] - save success response and update uiState
    private fun displayDogBreedList() {
        Timber.d("displayDogBreedList: launch In")
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getDogBreedListUseCase.invoke().collect { result ->
                when (result) {
                    is DogBreedListResult.Data -> {
                        delay(6000)
                        updateData(result)
                    }

                    is DogBreedListResult.Error -> {
                        logDogBreedListResult("Unknown error occurred")
                    }

                    is DogBreedListResult.NetworkError -> {
                        logDogBreedListResult("Network error occurred")
                    }
                }
                // Stop loading regardless of branch entered
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun updateData(result: DogBreedListResult.Data) {
        _uiState.update { currentState ->
            currentState.copy(
                items = result.dogBreedsList.map { DogBreed(name = it.name.capitalize(it)) },
            )
        }
    }

    private fun String.capitalize(it: DogBreed) = it.name.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.ROOT
        ) else it.toString()
    }

    private fun logDogBreedListResult(errorMessage: String) {
        _uiState.update {
            it.copy(
                errorMessage = errorMessage
            )
        }
    }
}
