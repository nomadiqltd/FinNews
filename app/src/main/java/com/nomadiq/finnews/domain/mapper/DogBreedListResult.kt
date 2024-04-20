package com.nomadiq.finnews.domain.mapper

import com.nomadiq.finnews.domain.model.ArticleFeedItem
import com.nomadiq.finnews.domain.model.DogBreed

/**
 * @author Michael Akakpo
 *
 *[DogBreedListResult]
 *
 * This will be used to store the result when a list of [DogBreed] is retrieved from the API
 **/

sealed class DogBreedListResult {
    data class Data(
        val dogBreedsList: List<ArticleFeedItem>
    ) : DogBreedListResult()

    data class Error(
        val error: String
    ) : DogBreedListResult()

    data object NetworkError : DogBreedListResult()
}