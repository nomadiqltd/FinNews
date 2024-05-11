package com.nomadiq.finnews.data.repository

import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.model.NewsArticleItemDetail
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository
import com.nomadiq.finnews.utils.TestConstants.UNKNOWN_ERROR
import com.nomadiq.finnews.utils.listOfArticles

/**
 *  @author Michael Akakpo
 *
 *  Fake NewsArticleFeedRepository implementation to simplify testing other components that depend on it
 *
 */

class FakeNewsArticleFeedRepositoryImpl : NewsArticleFeedRepository {

    companion object {

        // Trigger fake error result
        private var hasErrorTriggered = false

        // Default result set for list of articles
        private val resultListOfArticles =
            NewsArticleFeedListResult.Success(
                itemsList = listOfArticles
            )

        // Default result set for opened article detail
        private val resultArticleDetail =
            NewsArticleItemDetailResult.Success(
                NewsArticleItemDetail()
            )
    }

    override suspend fun fetchNewsArticleFeed(): NewsArticleFeedListResult {
        return if (hasErrorTriggered) {
            NewsArticleFeedListResult.Error(UNKNOWN_ERROR)
        } else {
            loadNewsArticleItemDetailData()
        }
    }

    private fun loadNewsArticleItemDetailData(): NewsArticleFeedListResult {
        return resultListOfArticles
    }

    override suspend fun fetchNewsArticleItemDetail(apiUrl: String): NewsArticleItemDetailResult {
        return if (hasErrorTriggered) {
            NewsArticleItemDetailResult.Error(UNKNOWN_ERROR)
        } else {
            return loadNewsArticleItemDetail()
        }
    }

    private fun loadNewsArticleItemDetail(): NewsArticleItemDetailResult {
        return resultArticleDetail
    }
}