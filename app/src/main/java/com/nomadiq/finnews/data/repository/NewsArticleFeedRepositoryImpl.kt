package com.nomadiq.finnews.data.repository

import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * This [NewsArticleFeedRepository] utilises the remote data source (and potentially others)
 * to aggregate requested data from the [Guardian Api].
 *
 */
class NewsArticleFeedRepositoryImpl @Inject constructor(
    val datasource: RemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) :
    NewsArticleFeedRepository {

    override suspend fun fetchNewsArticleFeed(): NewsArticleFeedListResult =
        withContext(ioDispatcher) { datasource.fetchNewsArticleFeed() }

    override suspend fun fetchNewsArticleItemDetail(apiUrl: String): NewsArticleItemDetailResult =
        withContext(ioDispatcher) { datasource.fetchNewsArticleItemDetail(apiUrl = apiUrl) }
}
