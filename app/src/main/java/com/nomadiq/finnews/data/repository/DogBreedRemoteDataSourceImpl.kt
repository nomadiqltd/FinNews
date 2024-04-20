package com.nomadiq.finnews.data.repository

import android.content.Context
import android.util.Log
import com.nomadiq.finnews.data.api.ApiService
import com.nomadiq.finnews.data.mapper.DogBreedListMapper
import com.nomadiq.finnews.data.mapper.DogBreedRandomImageListMapper
import com.nomadiq.finnews.domain.mapper.DogBreedListResult
import com.nomadiq.finnews.data.api.ResultStatus
import com.nomadiq.finnews.data.dto.NewsApiResponse
import com.nomadiq.finnews.data.model.DogBreedApiResponse
import com.nomadiq.finnews.data.model.DogBreedRandomImagesResponse
import com.nomadiq.finnews.data.network.connectivity.ConnectivityMonitor
import com.nomadiq.finnews.domain.mapper.DogBreedRandomImageResult
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * This data source fetches data remotely,
 * processes the result and allows retrieval and mapping of the API data from the [dog ceo api]
 *
 */
class DogBreedRemoteDataSourceImpl @Inject constructor(
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

    private val dogBreedListMapper: DogBreedListMapper by lazy {
        DogBreedListMapper()
    }

    private val dogRandomImageListMapper: DogBreedRandomImageListMapper by lazy {
        DogBreedRandomImageListMapper()
    }

    override suspend fun fetchAllDogBreeds(): DogBreedListResult {
        val result = fetchAllDogBreedResult()
        return dogBreedListMapper.map(result)
    }

    override suspend fun fetchRandomImagesByDogBreed(breed: String): DogBreedRandomImageResult {
        val result = fetchRandomImagesByDogBreedResult(breed = breed)
        return dogRandomImageListMapper.map(result)
    }

    private suspend fun fetchAllDogBreedResult(): ResultStatus<NewsApiResponse> {
        val response = apiService.fetchAllDogBreeds()
        return buildResultStatusFrom(response)
    }

    private suspend fun fetchRandomImagesByDogBreedResult(breed: String): ResultStatus<DogBreedRandomImagesResponse> {
        val response = apiService.fetchRandomImagesByDogBreed(breed = breed)
        return buildResultStatusFrom(response)
    }
}
