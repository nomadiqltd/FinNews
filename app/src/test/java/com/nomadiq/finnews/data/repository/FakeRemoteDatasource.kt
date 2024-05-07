package com.nomadiq.finnews.data.repository

import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.model.NewsArticleItemDetail

/**
 *  @author Michael Akakpo
 *
 *  Fake Success source implementation to simplify testing other components that depend on it
 *
 */

class FakeRemoteDataSource : RemoteDataSource {
    companion object {

        var generateError = false
        private const val requestError = "errorCode = 404, DogBreed list data not found"

        val result =
            NewsArticleFeedListResult.Data(
                listOf(

                )
            )

        // Default result set for list of random Dog images based on a particular breed
        private val resultListRandomImages =
            NewsArticleItemDetailResult.Data(
                NewsArticleItemDetail()
            )
    }

    override suspend fun fetchNewsArticleFeed(): NewsArticleFeedListResult {
        return if (generateError) {
            NewsArticleFeedListResult.Error(requestError)
        } else {
            loadNewsArticleFeedListData()
        }
    }

    private fun loadNewsArticleFeedListData(): NewsArticleFeedListResult {
        return result
    }

    override suspend fun fetchNewsArticleItemDetail(breed: String): NewsArticleItemDetailResult {
        return resultListRandomImages
    }
}