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
                listOf(
                    NewsArticleItemDetail("https://images.dog.ceo/breeds/hound-afghan/n02088094_251.jpg"),
                    NewsArticleItemDetail("https://images.dog.ceo/breeds/hound-afghan/n02088094_4396.jpg"),
                    NewsArticleItemDetail("https://images.dog.ceo/breeds/hound-basset/n02088238_13222.jpg"),
                    NewsArticleItemDetail("https://images.dog.ceo/breeds/hound-blood/n02088466_7004.jpg"),
                    NewsArticleItemDetail("https://images.dog.ceo/breeds/hound-blood/n02088466_7800.jpg"),
                    NewsArticleItemDetail("https://images.dog.ceo/breeds/hound-ibizan/n02091244_1541.jpg"),
                    NewsArticleItemDetail("https://images.dog.ceo/breeds/hound-ibizan/n02091244_2464.jpg"),
                    NewsArticleItemDetail("https://images.dog.ceo/breeds/hound-ibizan/n02091244_716.jpg"),
                    NewsArticleItemDetail("https://images.dog.ceo/breeds/hound-walker/n02089867_1430.jpg"),
                    NewsArticleItemDetail("https://images.dog.ceo/breeds/hound-walker/n02089867_1790.jpg"),
                )
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