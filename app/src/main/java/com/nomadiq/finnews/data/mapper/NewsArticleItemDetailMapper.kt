package com.nomadiq.finnews.data.mapper

import com.nomadiq.finnews.data.api.NetworkResult
import com.nomadiq.finnews.data.model.articledetail.NewsArticleDetailApiResponse
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.model.NewsArticleItemDetail


/**
 * @author Michael Akakpo
 *
 * This Mapper converts the initial [NetworkResult] from the API request into a lightweight domain friendly [NewsArticleItemDetailResult]
 * which in turn will be used within the ViewModel to allow a prefixed set of outcomes from the result.
 *
 * This will be used to store the result from when a random image of a particular [NewsArticleItemDetail] is requested from the API
 *
 */
class NewsArticleItemDetailMapper :
    Mapper<NetworkResult<NewsArticleDetailApiResponse>, NewsArticleItemDetailResult> {

    override fun map(value: NetworkResult<NewsArticleDetailApiResponse>): NewsArticleItemDetailResult {
        return when (value) {
            is NetworkResult.Success -> NewsArticleItemDetailResult.Success(
                createDataFromResponseResult(
                    value.result
                )
            )
            is NetworkResult.Error -> NewsArticleItemDetailResult.Error(value.error)
            is NetworkResult.NetworkError -> NewsArticleItemDetailResult.NetworkError()
        }
    }

    private fun createDataFromResponseResult(data: NewsArticleDetailApiResponse): NewsArticleItemDetail {
        return NewsArticleItemDetail(
            imgUrl = data.response.content.fields.thumbnail,
            title = data.response.content.fields.headline,
            date = data.response.content.webPublicationDate,
            body = data.response.content.fields.body
        )
    }
}
