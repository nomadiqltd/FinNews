package com.nomadiq.finnews.data.repository

import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.model.NewsArticleFeedItem
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
        private val newsArticleFeedListResult =
            NewsArticleFeedListResult.Success(
                listOf(
                    NewsArticleFeedItem("Article 1", "16th Apr, 2024 - 4 min read", "ghg", ""),
                    NewsArticleFeedItem("Article 2", "17th Apr, 2024 - 4 min read", "", ""),
                    NewsArticleFeedItem("Article 3", "18th Apr, 2024 - 4 min read", "", ""),
                    NewsArticleFeedItem("Article 4", "19th Apr, 2024 - 4 min read", "", "")
                )
            )

        // Default result set for list of random Dog images based on a particular breed
        private val newsArticleItemDetailResult =
            NewsArticleItemDetailResult.Success(
                NewsArticleItemDetail("https://images.dog.ceo/breeds/hound-afghan/n02088094_251.jpg"),
            )
    }

    override suspend fun fetchNewsArticleFeed(): NewsArticleFeedListResult {
        return if (generateError) {
            NewsArticleFeedListResult.Error(requestError)
        } else {
            return loadNewsArticleItemDetailData()
        }
    }

    private fun loadNewsArticleItemDetailData(): NewsArticleFeedListResult {
        return newsArticleFeedListResult
    }

    override suspend fun fetchNewsArticleItemDetail(apiUrl: String): NewsArticleItemDetailResult {
        return newsArticleItemDetailResult
    }
}