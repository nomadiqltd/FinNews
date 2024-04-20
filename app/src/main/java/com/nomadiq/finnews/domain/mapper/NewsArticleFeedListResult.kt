package com.nomadiq.finnews.domain.mapper

import com.nomadiq.finnews.domain.model.ArticleFeedItem

/**
 * @author Michael Akakpo
 *
 *[NewsArticleFeedListResult]
 *
 * This will be used to store the result when a list of [ArticleFeedItem] is retrieved from the API
 **/

sealed class NewsArticleFeedListResult {
    data class Data(
        val newsArticleFeedList: List<ArticleFeedItem>
    ) : NewsArticleFeedListResult()

    data class Error(
        val error: String
    ) : NewsArticleFeedListResult()

    data object NetworkError : NewsArticleFeedListResult()
}