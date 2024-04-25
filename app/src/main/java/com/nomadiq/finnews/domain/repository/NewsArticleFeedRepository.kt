package com.nomadiq.finnews.domain.repository

import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult

/**
 * @author Michael Akakpo
 *
 * This interface enables access to and allows CRUD operations on objects being passed through the domain layer (performed by the data layer)
 */
interface NewsArticleFeedRepository {
    suspend fun fetchNewsArticleFeed(): NewsArticleFeedListResult
    suspend fun fetchNewsArticleItemDetail(apiUrl: String): NewsArticleItemDetailResult
}
