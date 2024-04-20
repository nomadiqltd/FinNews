package com.nomadiq.finnews.domain.usecase

import com.nomadiq.finnews.data.network.connectivity.ConnectivityMonitor
import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * Use case for loading a list of [NewsArticleFeedItem] for user to view within a list
 */

class GetNewsArticleFeedUseCase @Inject constructor(
    private val newsArticleFeedRepository: NewsArticleFeedRepository,
    private val connectivityMonitor: ConnectivityMonitor,
) {

    suspend operator fun invoke(): Flow<NewsArticleFeedListResult> {
        return if (connectivityMonitor.isConnected()) {
            val dogBreedListResult = newsArticleFeedRepository.fetchNewsArticleFeed()
            flow {
                emit(dogBreedListResult)
            }
        } else {
            flowOf(NewsArticleFeedListResult.NetworkError)
        }
    }
}

