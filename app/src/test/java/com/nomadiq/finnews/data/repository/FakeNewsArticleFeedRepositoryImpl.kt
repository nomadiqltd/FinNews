package com.nomadiq.finnews.data.repository

import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.finnews.domain.model.DogBreed
import com.nomadiq.finnews.domain.model.DogBreedImageDetail
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository

/**
 *  @author Michael Akakpo
 *
 *  Fake NewsArticleFeedRepository implementation to simplify testing other components that depend on it
 *
 */

class FakeNewsArticleFeedRepositoryImpl : NewsArticleFeedRepository {

    protected var generateError = false

    companion object {
        private const val requestError = "errorCode = 404, DogBreed list data not found"

        // Default result set for list of Dogs
        private val resultList =
            NewsArticleFeedListResult.Data(
                listOf(
                )
            )

        // Default result set for list of random Dog images based on a particular breed
        private val resultListRandomImages =
            DogBreedRandomImageResult.Data(
                listOf(
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-afghan/n02088094_251.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-afghan/n02088094_4396.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-basset/n02088238_13222.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-blood/n02088466_7004.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-blood/n02088466_7800.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-ibizan/n02091244_1541.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-ibizan/n02091244_2464.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-ibizan/n02091244_716.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-walker/n02089867_1430.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-walker/n02089867_1790.jpg"),
                )
            )
    }

    override suspend fun fetchNewsArticleFeed(): NewsArticleFeedListResult {
        return if (generateError) {
            NewsArticleFeedListResult.Error(requestError)
        } else {
            return loadFakeDogBreedListData()
        }
    }

    private fun loadFakeDogBreedListData(): NewsArticleFeedListResult {
        return resultList
    }

    override suspend fun fetchRandomImagesByDogBreed(breed: String): DogBreedRandomImageResult {
        return resultListRandomImages
    }
}