package com.nomadiq.finnews.data.repository

import androidx.annotation.VisibleForTesting
import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.model.NewsArticleItemDetail
import com.nomadiq.finnews.utils.TestConstants
import com.nomadiq.finnews.utils.listOfArticles

/**
 *  @author Michael Akakpo
 *
 *  Fake Remote data source implementation to simplify testing other components that depend on it
 *
 */

class FakeRemoteDataSource : RemoteDataSource {

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
            NewsArticleFeedListResult.Error(TestConstants.UNKNOWN_ERROR)
        } else {
            loadNewsArticleFeedListData()
        }
    }

    private fun loadNewsArticleFeedListData(): NewsArticleFeedListResult {
        return resultListOfArticles
    }

    override suspend fun fetchNewsArticleItemDetail(apiUrl: String): NewsArticleItemDetailResult {
        return resultArticleDetail
    }

    @VisibleForTesting
    internal fun onErrorTriggered(value: Boolean) {
        hasErrorTriggered = value
    }
}