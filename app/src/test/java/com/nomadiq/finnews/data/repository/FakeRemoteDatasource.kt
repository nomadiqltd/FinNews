package com.nomadiq.finnews.data.repository

import com.nomadiq.finnews.domain.mapper.DogBreedListResult
import com.nomadiq.finnews.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.finnews.domain.model.DogBreed
import com.nomadiq.finnews.domain.model.DogBreedImageDetail

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
            DogBreedListResult.Data(
                listOf(
                    DogBreed("affenpinscher"),
                    DogBreed("african"),
                    DogBreed("airedal"),
                )
            )

        // Default result set for list of random Dog images based on a particular breed
        private val resultListRandomImages =
            DogBreedRandomImageResult.Data(
                listOf(
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-afghan/n02088094_251.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-afghan/n02088094_4396.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-basset/n02088238_13222.jpg"),
                )
            )
    }

    override suspend fun fetchAllDogBreeds(): DogBreedListResult {
        return if (generateError) {
            DogBreedListResult.Error(requestError)
        } else {
            loadDogBreedListData()
        }
    }

    private fun loadDogBreedListData(): DogBreedListResult {
        return result
    }

    override suspend fun fetchRandomImagesByDogBreed(breed: String): DogBreedRandomImageResult {
        return resultListRandomImages
    }
}