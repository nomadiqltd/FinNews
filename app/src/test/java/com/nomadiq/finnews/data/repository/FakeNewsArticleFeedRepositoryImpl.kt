package com.nomadiq.finnews.data.repository

import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.model.NewsArticleItemDetail
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository

/**
 *  @author Michael Akakpo
 *
 *  Fake NewsArticleFeedRepository implementation to simplify testing other components that depend on it
 *
 */

class FakeNewsArticleFeedRepositoryImpl : NewsArticleFeedRepository {

    protected var generateError = false

    companion object {
        private const val requestError = "errorCode = 404, DogBreed list data not found"

        // Default result set for list of Dogs
        private val resultList =
            NewsArticleFeedListResult.Data(
                listOf(
                )
            )

        // Default result set for list of random Dog images based on a particular breed
        private val resultListRandomImages =
            NewsArticleItemDetailResult.Data(
                    NewsArticleItemDetail("https://images.dog.ceo/breeds/hound-afghan/n02088094_251.jpg"),
            )
    }

    override suspend fun fetchNewsArticleFeed(): NewsArticleFeedListResult {
        return if (generateError) {
            NewsArticleFeedListResult.Error(requestError)
        } else {
            return loadFakeDogBreedListData()
        }
    }

    private fun loadFakeDogBreedListData(): NewsArticleFeedListResult {
        return resultList
    }

    override suspend fun fetchNewsArticleItemDetail(breed: String): NewsArticleItemDetailResult {
        return resultListRandomImages
    }
}