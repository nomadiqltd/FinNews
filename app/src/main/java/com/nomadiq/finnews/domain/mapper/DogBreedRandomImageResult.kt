package com.nomadiq.finnews.domain.mapper

import com.nomadiq.finnews.domain.model.DogBreedImageDetail

/**
 * @author Michael Akakpo
 *
 *[DogBreedRandomImageResult]
 *
 * This will be used to store the result from when a random image of a particular [DogBreed] is requested from the API
 **/

sealed class DogBreedRandomImageResult {
    data class Data(
        val items: List<DogBreedImageDetail>
    ) : DogBreedRandomImageResult()

    data class Error(
        val error: String
    ) : DogBreedRandomImageResult()

    data object NetworkError : DogBreedRandomImageResult()
}