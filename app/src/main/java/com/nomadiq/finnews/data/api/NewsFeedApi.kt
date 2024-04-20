package com.nomadiq.finnews.data.api

import com.nomadiq.finnews.BuildConfig.*
import com.nomadiq.finnews.data.dto.NewsArticleFeedApiResponse
import com.nomadiq.finnews.data.model.DogBreedRandomImagesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author - Michael Akakpo
 *
 * A public interface that exposes the [Guardian News Api] to the client
 *
 */
interface NewsFeedApi {

    @GET("breed/{breed}/images/random/10")
    suspend fun fetchRandomImagesByDogBreed(@Path("breed") breed: String): Response<DogBreedRandomImagesResponse>

    @GET("search?show-fields=headline,thumbnail&page-size=25&api-key=$API_KEY&q=finance")
    suspend fun fetchNewsArticleFeed(): Response<NewsArticleFeedApiResponse>
}
