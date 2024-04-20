package com.nomadiq.finnews.data.repository

import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * This [NewsArticleFeedRepository] utilises the remote data source (and potentially others)
 * to aggregate requested data from the [Dog Api].
 *
 */
class NewsArticleFeedRepositoryImpl @Inject constructor(
    val datasource: RemoteDataSource,
) :
    NewsArticleFeedRepository {

    override suspend fun fetchNewsArticleFeed(): NewsArticleFeedListResult =
        datasource.fetchNewsArticleFeed()

    override suspend fun fetchRandomImagesByDogBreed(breed: String): DogBreedRandomImageResult =
        datasource.fetchRandomImagesByDogBreed(breed = breed)
}
