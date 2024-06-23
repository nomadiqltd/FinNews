package com.nomadiq.finnews.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomadiq.finnews.domain.usecase.GetNewsArticleFeedUseCase
import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.model.NewsArticleFeedItem
import com.nomadiq.finnews.presentation.utils.ErrorType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * @author - Michael Akakpo
 *
 * [NewsArticleFeedViewModel]
 *
 * Defines UI State information about the list of [NewsArticleFeedItem]
 * retrieved from the API and make it accessible to Compose and other UI components
 *
 */

@HiltViewModel
class NewsArticleFeedViewModel @Inject constructor(
    private val getNewsArticleFeedUseCase: GetNewsArticleFeedUseCase
) :
    ViewModel() {
    companion object {
        const val NETWORK_ERROR_MESSAGE = "Connectivity issues, please check your connection"
    }

    // Define UIState variable for [NewsArticleFeedItem] data to map to the equivalent UI Screens and components
    private val _uiState =
        MutableStateFlow(
            NewsArticleFeedUiState(
                items = listOf(),
            )
        )
    val uiState: StateFlow<NewsArticleFeedUiState> = _uiState.asStateFlow()

    init {
        onDisplayNewsArticleFeedList()
    }

    // Function to fetch List of [NewsArticleFeedItem] - handle response and update uiState accordingly
    @VisibleForTesting
    fun onDisplayNewsArticleFeedList(): StateFlow<NewsArticleFeedUiState> {
        Timber.d("displayNewsArticleFeedList")
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getNewsArticleFeedUseCase.invoke().collect { result ->
                when (result) {
                    is NewsArticleFeedListResult.Success -> {
                        updateData(result)
                    }

                    is NewsArticleFeedListResult.Error -> {
                        logNewsArticleFeedResult(errorMessage = result.error)
                    }

                    is NewsArticleFeedListResult.NetworkError -> {
                        logNewsArticleFeedResult(
                            errorMessage = NETWORK_ERROR_MESSAGE,
                            isNetwork = true
                        )
                    }
                }
                // Stop loading regardless of branch entered
                _uiState.update { it.copy(isLoading = false) }
            }
        }
        return _uiState.asStateFlow()
    }

    private fun updateData(result: NewsArticleFeedListResult.Success) {
        _uiState.update { currentState ->
            currentState.copy(
                items = result.itemsList.map {
                    NewsArticleFeedItem(
                        title = it.title,
                        subtitle = it.subtitle,
                        imgUrl = it.imgUrl,
                        apiUrl = it.apiUrl,
                    )
                },
            )
        }
    }

    private fun logNewsArticleFeedResult(errorMessage: String, isNetwork: Boolean = false) {
        _uiState.update {
            it.copy(
                errorMessage = errorMessage,
                errorState = if (isNetwork) ErrorType.NETWORK_ERROR else ErrorType.GENERAL_ERROR
            )
        }
    }
}
