package com.nomadiq.finnews.data.repository

import android.content.Context
import com.nomadiq.finnews.data.api.ApiService
import com.nomadiq.finnews.data.mapper.NewsArticleFeedListMapper
import com.nomadiq.finnews.data.mapper.DogBreedRandomImageListMapper
import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.data.api.ResultStatus
import com.nomadiq.finnews.data.dto.NewsArticleFeedApiResponse
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

    private val dogRandomImageListMapper: DogBreedRandomImageListMapper by lazy {
        DogBreedRandomImageListMapper()
    }

    override suspend fun fetchNewsArticleFeed(): NewsArticleFeedListResult {
        val result = fetchNewsArticleFeedResult()
        return newsArticleFeedListMapper.map(result)
    }

    override suspend fun fetchRandomImagesByDogBreed(breed: String): DogBreedRandomImageResult {
        val result = fetchRandomImagesByDogBreedResult(breed = breed)
        return dogRandomImageListMapper.map(result)
    }

    private suspend fun fetchNewsArticleFeedResult(): ResultStatus<NewsArticleFeedApiResponse> {
        val response = apiService.fetchNewsArticleFeed()
        return buildResultStatusFrom(response)
    }

    private suspend fun fetchRandomImagesByDogBreedResult(breed: String): ResultStatus<DogBreedRandomImagesResponse> {
        val response = apiService.fetchRandomImagesByDogBreed(breed = breed)
        return buildResultStatusFrom(response)
    }
}
