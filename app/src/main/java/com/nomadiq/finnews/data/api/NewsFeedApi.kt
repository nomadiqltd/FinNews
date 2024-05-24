package com.nomadiq.finnews.data.api

import com.nomadiq.finnews.BuildConfig.*
import com.nomadiq.finnews.data.model.articledetail.NewsArticleDetailApiResponse
import com.nomadiq.finnews.data.model.article.NewsArticleFeedApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * @author - Michael Akakpo
 *
 * A public interface that exposes the [Guardian News Api] to the client
 *
 */
interface NewsFeedApi {
    @GET
    suspend fun fetchNewsArticleItemDetail(@Url apiUrl: String): Response<NewsArticleDetailApiResponse>

    @GET("search?show-fields=headline,thumbnail&page-size=25&api-key=$API_KEY&q=finance")
    suspend fun fetchNewsArticleFeed(): Response<NewsArticleFeedApiResponse>
}
