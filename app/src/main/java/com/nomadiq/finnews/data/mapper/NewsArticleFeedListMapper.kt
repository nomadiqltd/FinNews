package com.nomadiq.finnews.data.mapper

import com.nomadiq.finnews.data.api.NetworkResult
import com.nomadiq.finnews.data.model.article.NewsArticleFeedApiResponse
import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.model.NewsArticleFeedItem


/**
 * @author Michael Akakpo
 *
 * This Mapper converts the initial [NetworkResult] from the API request into a lightweight domain friendly [NewsArticleFeedListResult]
 * which in turn will be used within the ViewModel to allow a prefixed set of outcomes from the result.
 *
 */

class NewsArticleFeedListMapper :
    Mapper<NetworkResult<NewsArticleFeedApiResponse>, NewsArticleFeedListResult> {

    override fun map(value: NetworkResult<NewsArticleFeedApiResponse>): NewsArticleFeedListResult {
        return when (value) {
            is NetworkResult.Success -> NewsArticleFeedListResult.Success(
                createDataFromResponseResult(
                    value.result
                )
            )

            is NetworkResult.Error -> NewsArticleFeedListResult.Error(value.error)
            is NetworkResult.NetworkError -> NewsArticleFeedListResult.NetworkError()
        }
    }

    private fun createDataFromResponseResult(data: NewsArticleFeedApiResponse): List<NewsArticleFeedItem> {
        val list = mutableListOf<NewsArticleFeedItem>()
        val results = data.response.results
        results.forEach { item ->
            list.addAll(
                listOf(
                    NewsArticleFeedItem(
                        title = item.fields.headline,
                        subtitle = item.webPublicationDate,
                        imgUrl = item.fields.thumbnail,
                        apiUrl = item.apiUrl
                    ),
                )
            )
        }
        return list
    }
}
