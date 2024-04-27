package com.nomadiq.finnews.data.repository

import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository


/**
 * @author Michael Akakpo
 *
 * This remote data source, fetches data for the [NewsArticleFeedRepository]
 * from a remote source which is the Guardian ceo api.
 *
 */
interface RemoteDataSource {
    suspend fun fetchNewsArticleFeed(): NewsArticleFeedListResult
    suspend fun fetchNewsArticleItemDetail(apiUrl: String): NewsArticleItemDetailResult
}
