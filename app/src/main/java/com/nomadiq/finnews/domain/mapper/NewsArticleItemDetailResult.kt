package com.nomadiq.finnews.domain.mapper

import com.nomadiq.finnews.domain.model.NewsArticleItemDetail

/**
 * @author Michael Akakpo
 *
 *[NewsArticleItemDetailResult]
 *
 * This will be used to store the result from when a random image of a particular [NewsArticleItemDetail] is requested from the API
 **/

sealed class NewsArticleItemDetailResult {
    data class Success(
        val item: NewsArticleItemDetail
    ) : NewsArticleItemDetailResult()

    data class Error(
        val error: String
    ) : NewsArticleItemDetailResult()

    data class NetworkError(val message: String = "") : NewsArticleItemDetailResult()
}
