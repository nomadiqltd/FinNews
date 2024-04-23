package com.nomadiq.finnews.data.repository

import android.content.Context
import com.nomadiq.finnews.data.api.ApiService
import com.nomadiq.finnews.data.mapper.NewsArticleFeedListMapper
import com.nomadiq.finnews.data.mapper.NewsArticleItemDetailMapper
import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.data.api.ResultStatus
import com.nomadiq.finnews.data.api.buildResultStatusFrom
import com.nomadiq.finnews.data.model.articledetail.NewsArticleDetailApiResponse
import com.nomadiq.finnews.data.model.article.NewsArticleFeedApiResponse
import com.nomadiq.finnews.data.network.connectivity.ConnectivityMonitor
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * This data source fetches data remotely,
 * processes the result and allows retrieval and mapping of the API data from the [dog ceo api]
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

    private val dogRandomImageListMapper: NewsArticleItemDetailMapper by lazy {
        NewsArticleItemDetailMapper()
    }

    override suspend fun fetchNewsArticleFeed(): NewsArticleFeedListResult {
        val result = fetchNewsArticleFeedResult()
        return newsArticleFeedListMapper.map(result)
    }

    override suspend fun fetchNewsArticleItemDetail(breed: String): NewsArticleItemDetailResult {
        val result = fetchNewsArticleItemDetailResult(apiUrl = breed)
        return dogRandomImageListMapper.map(result)
    }

    private suspend fun fetchNewsArticleFeedResult(): ResultStatus<NewsArticleFeedApiResponse> {
        val response = apiService.fetchNewsArticleFeed()
        return buildResultStatusFrom(response)
    }

    private suspend fun fetchNewsArticleItemDetailResult(apiUrl: String): ResultStatus<NewsArticleDetailApiResponse> {
        val response = apiService.fetchNewsArticleItemDetail(apiUrl = apiUrl)
        return buildResultStatusFrom(response)
    }
}
