package com.nomadiq.finnews.data.mapper

import com.nomadiq.finnews.data.api.ResultStatus
import com.nomadiq.finnews.data.model.articledetail.NewsArticleDetailApiResponse
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.model.NewsArticleItemDetail
import timber.log.Timber


/**
 * @author Michael Akakpo
 *
 * This Mapper converts the initial [ResultStatus] from the API request into a lightweight domain friendly [NewsArticleItemDetailResult]
 * which in turn will be used within the ViewModel to allow a prefixed set of outcomes from the result.
 *
 * This will be used to store the result from when a random image of a particular [NewsArticleItemDetail] is requested from the API
 *
 */
class NewsArticleItemDetailMapper :
    Mapper<ResultStatus<NewsArticleDetailApiResponse>, NewsArticleItemDetailResult> {

    override fun map(value: ResultStatus<NewsArticleDetailApiResponse>): NewsArticleItemDetailResult {
        return when (value) {
            is ResultStatus.Success -> NewsArticleItemDetailResult.Data(
                createDataFromResponseResult(
                    value.result
                )
            )
            is ResultStatus.Error -> NewsArticleItemDetailResult.Error(value.error)
            is ResultStatus.NetworkError -> NewsArticleItemDetailResult.NetworkError
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
