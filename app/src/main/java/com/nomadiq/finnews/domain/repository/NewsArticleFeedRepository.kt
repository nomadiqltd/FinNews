package com.nomadiq.finnews.domain.repository

import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.finnews.domain.model.DogBreed

/**
 * @author Michael Akakpo
 *
 * This interface enables access to and allows CRUD operations on [DogBreed] being passed through the domain layer (performed by the data layer)
 */
interface NewsArticleFeedRepository {
    suspend fun fetchNewsArticleFeed(): NewsArticleFeedListResult
    suspend fun fetchRandomImagesByDogBreed(breed: String): DogBreedRandomImageResult
}
