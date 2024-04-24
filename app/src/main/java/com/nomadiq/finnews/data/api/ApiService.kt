package com.nomadiq.finnews.data.api

import android.annotation.SuppressLint
import android.content.Context
import com.nomadiq.finnews.BuildConfig.API_KEY
import com.nomadiq.finnews.data.model.articledetail.NewsArticleDetailApiResponse
import com.nomadiq.finnews.data.model.article.NewsArticleFeedApiResponse
import com.nomadiq.finnews.data.network.connectivity.ConnectivityMonitor
import retrofit2.Response

/**
 *
 * @author - Michael Akakpo
 *
 * @ApiService - The object interfacing with the API endpoint to retrieve data for the data sources
 *
 */

class ApiService(
    private val context: Context? = null,
    private val connectivityMonitor: ConnectivityMonitor,
) {

    private val apiClient = ApiClient(
        connectivityMonitor = connectivityMonitor,
    ).apply {
        // logger = { it -> Timber.d(it) }
    }

    // News Feed API
    private val newsFeedApi: NewsFeedApi by lazy {
        apiClient.createService(NewsFeedApi::class.java)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: ApiService? = null

        fun get(
            context: Context?,
            connectivityMonitor: ConnectivityMonitor
        ): ApiService {
            return instance ?: synchronized(this) {
                instance ?: ApiService(
                    context?.applicationContext,
                    connectivityMonitor
                ).also { instance = it }
            }
        }
    }

    suspend fun fetchNewsArticleFeed(): Response<NewsArticleFeedApiResponse> {
        return newsFeedApi.fetchNewsArticleFeed()
    }

    suspend fun fetchNewsArticleItemDetail(apiUrl: String): Response<NewsArticleDetailApiResponse> {
        return newsFeedApi.fetchNewsArticleItemDetail(apiUrl = "$apiUrl?api-key=$API_KEY&show-fields=body,headline,thumbnail")
    }
}