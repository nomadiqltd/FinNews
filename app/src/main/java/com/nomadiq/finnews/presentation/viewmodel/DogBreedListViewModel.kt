package com.nomadiq.finnews.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomadiq.finnews.domain.usecase.GetDogBreedListUseCase
import com.nomadiq.finnews.domain.mapper.DogBreedListResult
import com.nomadiq.finnews.domain.model.ArticleFeedItem
import com.nomadiq.finnews.domain.model.DogBreed
import com.nomadiq.finnews.domain.model.NewsChannelFeedItem
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
    private val _uiState =
        MutableStateFlow(
            FinNewsFeedListUiState(
                listOf(),
                articleItems = defaultArticleList()
            )
        )
    val uiState: StateFlow<FinNewsFeedListUiState> = _uiState.asStateFlow()

    init {
        displayDogBreedList()
    }

    private fun defaultArticleList() = listOf(
        ArticleFeedItem(
            title = "News",
            subtitle = "",
            imgUrl = "https://media.guim.co.uk/b4b8ba7d544a93d10982353d581717bfdf7888ee/1_472_5303_3183/500.jpg"
        ),
        ArticleFeedItem(
            title = "News",
            subtitle = "",
            imgUrl = "https://media.guim.co.uk/b4b8ba7d544a93d10982353d581717bfdf7888ee/1_472_5303_3183/500.jpg"
        ),
        ArticleFeedItem(
            title = "News",
            subtitle = "",
            imgUrl = "https://media.guim.co.uk/b4b8ba7d544a93d10982353d581717bfdf7888ee/1_472_5303_3183/500.jpg"
        ),
        ArticleFeedItem(
            title = "News",
            subtitle = "",
            imgUrl = "https://media.guim.co.uk/b4b8ba7d544a93d10982353d581717bfdf7888ee/1_472_5303_3183/500.jpg"
        ),
        ArticleFeedItem(
            title = "News",
            subtitle = "",
            imgUrl = "https://media.guim.co.uk/b4b8ba7d544a93d10982353d581717bfdf7888ee/1_472_5303_3183/500.jpg"
        ),
        ArticleFeedItem(
            title = "News",
            subtitle = "",
            imgUrl = "https://media.guim.co.uk/b4b8ba7d544a93d10982353d581717bfdf7888ee/1_472_5303_3183/500.jpg"
        ),
        ArticleFeedItem(
            title = "News",
            subtitle = "",
            imgUrl = "https://media.guim.co.uk/b4b8ba7d544a93d10982353d581717bfdf7888ee/1_472_5303_3183/500.jpg"
        ),
        ArticleFeedItem(
            title = "News",
            subtitle = "",
            imgUrl = "https://media.guim.co.uk/b4b8ba7d544a93d10982353d581717bfdf7888ee/1_472_5303_3183/500.jpg"
        ),
        ArticleFeedItem(
            title = "News",
            subtitle = "",
            imgUrl = "https://media.guim.co.uk/b4b8ba7d544a93d10982353d581717bfdf7888ee/1_472_5303_3183/500.jpg"
        ),
        ArticleFeedItem(
            title = "News",
            subtitle = "",
            imgUrl = "https://media.guim.co.uk/b4b8ba7d544a93d10982353d581717bfdf7888ee/1_472_5303_3183/500.jpg"
        ),
    )


    // Function to fetch List of [DogBreed] - save success response and update uiState
    private fun displayDogBreedList() {
        Timber.d("displayDogBreedList: launch In")
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getDogBreedListUseCase.invoke().collect { result ->
                when (result) {
                    is DogBreedListResult.Data -> {
                        delay(4000)
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
                articleItems = defaultArticleList()
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
