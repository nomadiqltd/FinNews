package com.nomadiq.finnews.data.mapper

import android.util.Log
import com.nomadiq.finnews.data.api.ResultStatus
import com.nomadiq.finnews.data.dto.NewsApiResponse
import com.nomadiq.finnews.domain.model.DogBreed
import com.nomadiq.finnews.data.model.DogBreedApiResponse
import com.nomadiq.finnews.domain.mapper.DogBreedListResult
import com.nomadiq.finnews.domain.model.ArticleFeedItem


/**
 * @author Michael Akakpo
 *
 * This Mapper converts the initial [ResultStatus] from the API request into a lightweight domain friendly [DogBreedListResult]
 * which in turn will be used within the ViewModel to allow a prefixed set of outcomes from the result.
 *
 */

class DogBreedListMapper : Mapper<ResultStatus<NewsApiResponse>, DogBreedListResult> {

    override fun map(value: ResultStatus<NewsApiResponse>): DogBreedListResult {
        return when (value) {
            is ResultStatus.Success -> DogBreedListResult.Data(createDataFromResponseResult(value.result))
            is ResultStatus.Error -> DogBreedListResult.Error(value.error)
            is ResultStatus.NetworkError -> DogBreedListResult.NetworkError
        }
    }

    private fun createDataFromResponseResult(data: NewsApiResponse): List<ArticleFeedItem> {
        val list = mutableListOf<ArticleFeedItem>()
        val results = data.response.results
        results.forEach { item ->
            list.addAll(
                listOf(
                    ArticleFeedItem(
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
