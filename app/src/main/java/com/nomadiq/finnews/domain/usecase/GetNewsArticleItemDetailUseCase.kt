package com.nomadiq.finnews.domain.usecase

import com.nomadiq.finnews.data.network.connectivity.ConnectivityMonitor
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * Use case for fetching a list of [@NewsArticleItemDetail] from user selecting a particular breed within presentation layer
 *
 */

class GetNewsArticleItemDetailUseCase @Inject constructor(
    private val newsArticleFeedRepository: NewsArticleFeedRepository,
    private val connectivityMonitor: ConnectivityMonitor,
) {

    suspend operator fun invoke(apiUrl: String): Flow<NewsArticleItemDetailResult> {
        return if (connectivityMonitor.isConnected()) {
            val getNewsArticleItemDetailResult =
                newsArticleFeedRepository.fetchNewsArticleItemDetail(apiUrl = apiUrl)
            flow { emit(getNewsArticleItemDetailResult) }
        } else {
            flowOf(NewsArticleItemDetailResult.NetworkError())
        }
    }
}

