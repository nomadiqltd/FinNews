package com.nomadiq.finnews.data.repository

import android.content.Context
import com.nomadiq.finnews.data.api.ApiService
import com.nomadiq.finnews.data.mapper.NewsArticleFeedListMapper
import com.nomadiq.finnews.data.mapper.NewsArticleItemDetailMapper
import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.data.api.NetworkResult
import com.nomadiq.finnews.data.api.buildNetworkResult
import com.nomadiq.finnews.data.model.articledetail.NewsArticleDetailApiResponse
import com.nomadiq.finnews.data.model.article.NewsArticleFeedApiResponse
import com.nomadiq.finnews.data.network.connectivity.ConnectivityMonitor
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * This data source fetches data remotely,
 * processes the result and allows retrieval and mapping of the API data from the [api]
 *
 */
class NewsArticleRemoteDataSourceImpl @Inject constructor(
    private val context: Context,
    private val connectivityMonitor: ConnectivityMonitor,
    private val providedApiService: ApiService?,
) :
    RemoteDataSource {

    private val apiService by lazy {
        providedApiService ?: ApiService.get(
            context,
            connectivityMonitor
        )
    }

    private val newsArticleFeedListMapper: NewsArticleFeedListMapper by lazy {
        NewsArticleFeedListMapper()
    }

    private val newsArticleItemDetailMapper: NewsArticleItemDetailMapper by lazy {
        NewsArticleItemDetailMapper()
    }

    override suspend fun fetchNewsArticleFeed(): NewsArticleFeedListResult {
        val result = fetchNewsArticleFeedResult()
        return newsArticleFeedListMapper.map(result)
    }

    override suspend fun fetchNewsArticleItemDetail(apiUrl: String): NewsArticleItemDetailResult {
        val result = fetchNewsArticleItemDetailResult(apiUrl = apiUrl)
        return newsArticleItemDetailMapper.map(result)
    }

    private suspend fun fetchNewsArticleFeedResult(): NetworkResult<NewsArticleFeedApiResponse> {
        val response = apiService.fetchNewsArticleFeed()
        return buildNetworkResult(response)
    }

    private suspend fun fetchNewsArticleItemDetailResult(apiUrl: String): NetworkResult<NewsArticleDetailApiResponse> {
        val response = apiService.fetchNewsArticleItemDetail(apiUrl = apiUrl)
        return buildNetworkResult(response)
    }
}
