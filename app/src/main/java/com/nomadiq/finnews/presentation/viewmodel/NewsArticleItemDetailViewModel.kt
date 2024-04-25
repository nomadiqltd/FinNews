package com.nomadiq.finnews.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.model.NewsArticleItemDetail
import com.nomadiq.finnews.domain.usecase.GetNewsArticleItemDetailUseCase
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
 * [NewsArticleItemDetailViewModel]
 *
 * Defines UI State information about the details of the individual news article items [NewsArticleFeedItem]
 *
 */

@HiltViewModel
class NewsArticleItemDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getNewsArticleItemDetailUseCase: GetNewsArticleItemDetailUseCase
) : ViewModel() {

    // Define UIState variable for [NewsArticleItemDetail] data to map to the equivalent UI Screens and components
    private val _uiState = MutableStateFlow(NewsArticleItemUiState())
    val uiState: StateFlow<NewsArticleItemUiState> = _uiState.asStateFlow()

    val apiUrl: String =
        checkNotNull(savedStateHandle.getStateFlow(key = "id", initialValue = "")).value

    init {
        displayNewsArticleItemDetail(apiUrl)
    }

    // Function to fetch List of [NewsArticleItemDetail] - save success response and update uiState
    private fun displayNewsArticleItemDetail(apiUrl: String) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getNewsArticleItemDetailUseCase.invoke(validateURL(apiUrl)).collect { result ->
                when (result) {
                    is NewsArticleItemDetailResult.Data -> {
                        Timber.tag("NewsFeedItemDetail ")
                            .d("%s ", " SUCCESS ==> " + "opening article ${result.item.imgUrl}")
                        Log.d("NewsFeedItemDetail ", " SUCCESS ==> " + result.item.imgUrl)
                        updateData(result)
                    }

                    is NewsArticleItemDetailResult.Error -> {
                        Log.d(
                            "NewsFeedItemDetail ",
                            " ERROR ==> ${result.error} "
                        )
                    }

                    is NewsArticleItemDetailResult.NetworkError -> {
                    }
                }
            }
            _uiState.update { currentState -> currentState.copy(isLoading = false) }
        }
    }

    private fun updateData(result: NewsArticleItemDetailResult.Data) {
        _uiState.update { currentState ->
            currentState.copy(
                item = NewsArticleItemDetail(
                    title = result.item.title,
                    imgUrl = result.item.imgUrl,
                    body = result.item.body
                ),
            )
        }
    }
}
