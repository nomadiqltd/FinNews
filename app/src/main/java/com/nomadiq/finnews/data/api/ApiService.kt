package com.nomadiq.finnews.data.api

import android.annotation.SuppressLint
import android.content.Context
import com.nomadiq.finnews.data.dto.NewsApiResponse
import com.nomadiq.finnews.data.model.DogBreedApiResponse
import com.nomadiq.finnews.data.model.DogBreedRandomImagesResponse
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

    private val newFeedApi: NewsFeedApi by lazy {
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

    suspend fun fetchAllDogBreeds(): Response<NewsApiResponse> {
        return newFeedApi.fetchAllDogBreeds()
    }

    suspend fun fetchRandomImagesByDogBreed(breed: String): Response<DogBreedRandomImagesResponse> {
        return newFeedApi.fetchRandomImagesByDogBreed(breed)
    }
}