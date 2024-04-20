package com.nomadiq.finnews.data.repository

import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.mapper.DogBreedRandomImageResult


/**
 * @author Michael Akakpo
 *
 * This remote data source, fetches data for the [DogBreedRepository]
 * from a remote source which is the dog ceo api.
 *
 */
interface RemoteDataSource {
    suspend fun fetchNewsArticleFeed(): NewsArticleFeedListResult
    suspend fun fetchRandomImagesByDogBreed(breed: String): DogBreedRandomImageResult
}
