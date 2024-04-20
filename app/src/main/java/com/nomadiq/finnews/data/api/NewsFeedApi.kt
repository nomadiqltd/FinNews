package com.nomadiq.finnews.data.api

import com.nomadiq.finnews.data.dto.NewsApiResponse
import com.nomadiq.finnews.data.model.DogBreedRandomImagesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author - Michael Akakpo
 *
 * A public interface that exposes the [Guardian News Api] to the client
 *
 */
interface NewsFeedApi {

    //TODO - Hide API Key in buildConfigField
    companion object {
        const val API_KEY = "b77f75d9-492b-4d80-bab3-088e00fd5f7b"
    }

    @GET("breed/{breed}/images/random/10")
    suspend fun fetchRandomImagesByDogBreed(@Path("breed") breed: String): Response<DogBreedRandomImagesResponse>

    //  @GET("search?show-fields=headline,thumbnail&page-size=25&api-key=b77f75d9-492b-4d80-bab3-088e00fd5f7b&q=finance")
    @GET("search?show-fields=headline,thumbnail&page-size=25&api-key=$API_KEY&q=finance")
    suspend fun fetchAllDogBreeds(
//        @Query("q") searchQuery: String
    ): Response<NewsApiResponse>
}
