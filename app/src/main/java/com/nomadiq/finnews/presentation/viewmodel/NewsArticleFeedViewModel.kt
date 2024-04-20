package com.nomadiq.finnews.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomadiq.finnews.domain.usecase.GetNewsArticleFeedUseCase
import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.model.ArticleFeedItem
import com.nomadiq.finnews.domain.model.DogBreed
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
 * [NewsArticleFeedViewModel]
 *
 * Defines UI State information about the list of [DogBreed]
 * retrieved from the API and make it accessible to Compose and other UI components
 *
 */

@HiltViewModel
class NewsArticleFeedViewModel @Inject constructor(
    private val getNewsArticleFeedUseCase: GetNewsArticleFeedUseCase
) :
    ViewModel() {

    // Define UIState variable for [DogBreed] data to map to the equivalent UI Screens and components
    private val _uiState =
        MutableStateFlow(
            NewsArticleFeedUiState(
                items = listOf(),
            )
        )
    val uiState: StateFlow<NewsArticleFeedUiState> = _uiState.asStateFlow()

    init {
        displayDogBreedList()
    }

    private fun defaultArticleList() = listOf(
        ArticleFeedItem(
            title = "News",
            subtitle = "subtitle 1",
            imgUrl = "https://media.guim.co.uk/b4b8ba7d544a93d10982353d581717bfdf7888ee/1_472_5303_3183/500.jpg"
        ),
        ArticleFeedItem(
            title = "News",
            subtitle = "subtitle 2",
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
            getNewsArticleFeedUseCase.invoke().collect { result ->
                when (result) {
                    is NewsArticleFeedListResult.Data -> {
                        delay(4000)
                        Log.d(
                            "displayDogBreedList() ",
                            " SUCCESS ==> ${result.newsArticleFeedList.size} "
                        )
                        updateData(result)
                    }

                    is NewsArticleFeedListResult.Error -> {
                        Log.d("displayDogBreedList()", " ERROR ==> ${result.error} ")
                        logDogBreedListResult("Unknown error occurred")
                    }

                    is NewsArticleFeedListResult.NetworkError -> {
                        Log.d("displayDogBreedList()", "NETWORK ERROR ==> Network Error")
                        logDogBreedListResult("Network error occurred")
                    }
                }
                // Stop loading regardless of branch entered
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun updateData(result: NewsArticleFeedListResult.Data) {
        _uiState.update { currentState ->
            currentState.copy(
                items = result.newsArticleFeedList.map {
                    ArticleFeedItem(
                        title = it.title,
                        subtitle = it.subtitle,
                        imgUrl = it.imgUrl,
                        apiUrl = it.apiUrl,
                    )
                },
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
