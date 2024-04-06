package com.nomadiq.finnews.data.api

import android.content.Context
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

    internal val apiClient = ApiClient(
        connectivityMonitor = connectivityMonitor,
    ).apply {
        // logger = { it -> Timber.d(it) }
    }

    internal val dogBreedApi: DogBreedApi by lazy {
        apiClient.createService(DogBreedApi::class.java)
    }

    companion object {
        @Volatile
        private var instance: ApiService? = null // volatile to avoid rare java bug

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

    suspend fun fetchAllDogBreeds(): Response<DogBreedApiResponse> {
        return dogBreedApi.fetchAllDogBreeds()
    }

    suspend fun fetchRandomImagesByDogBreed(breed: String): Response<DogBreedRandomImagesResponse> {
        return dogBreedApi.fetchRandomImagesByDogBreed(breed)
    }
}