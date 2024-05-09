package com.nomadiq.finnews.domain.mapper

import com.nomadiq.finnews.domain.model.NewsArticleFeedItem

/**
 * @author Michael Akakpo
 *
 *[NewsArticleFeedListResult]
 *
 * This will be used to store the result when a list of [NewsArticleFeedItem] is retrieved from the API
 **/

sealed class NewsArticleFeedListResult {
    data class Success(
        val itemsList: List<NewsArticleFeedItem>
    ) : NewsArticleFeedListResult()

    data class Error(
        val error: String
    ) : NewsArticleFeedListResult()

    data class NetworkError(val message: String = "") : NewsArticleFeedListResult()
}