package com.nomadiq.finnews.data.repository

import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.finnews.domain.model.DogBreed
import com.nomadiq.finnews.domain.model.NewsArticleItemDetail

/**
 *  @author Michael Akakpo
 *
 *  Fake Success source implementation to simplify testing other components that depend on it
 *
 */

class FakeRemoteDataSource : RemoteDataSource {
    companion object {

        var generateError = false
        private const val requestError = "errorCode = 404, DogBreed list data not found"

        val result =
            NewsArticleFeedListResult.Data(
                listOf(

                )
            )

        // Default result set for list of random Dog images based on a particular breed
        private val resultListRandomImages =
            DogBreedRandomImageResult.Data(
                NewsArticleItemDetail()
            )
    }

    override suspend fun fetchNewsArticleFeed(): NewsArticleFeedListResult {
        return if (generateError) {
            NewsArticleFeedListResult.Error(requestError)
        } else {
            loadDogBreedListData()
        }
    }

    private fun loadDogBreedListData(): NewsArticleFeedListResult {
        return result
    }

    override suspend fun fetchRandomImagesByDogBreed(breed: String): DogBreedRandomImageResult {
        return resultListRandomImages
    }
}