package com.nomadiq.chipdogstask.data.api

import com.nomadiq.chipdogstask.data.model.DogBreedApiResponse
import com.nomadiq.chipdogstask.data.model.DogBreedRandomImagesResponse
import com.nomadiq.chipdogstask.data.network.NetworkConnectivityInterceptor
import com.nomadiq.chipdogstask.data.network.connectivity.ConnectivityMonitor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author - Michael Akakpo
 *
 * A public interface that exposes the [Dog Ceo Api] to the client
 *
 */
interface DogBreedApi {
    @GET("breed/{breed}/images/random/10")
    suspend fun fetchRandomImagesByDogBreed(@Path("breed") breed: String): Response<DogBreedRandomImagesResponse>

    @GET("breeds/list/all")
    suspend fun fetchAllDogBreeds(): Response<DogBreedApiResponse>
}
