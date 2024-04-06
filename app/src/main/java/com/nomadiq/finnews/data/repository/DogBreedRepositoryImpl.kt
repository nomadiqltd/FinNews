package com.nomadiq.finnews.data.repository

import com.nomadiq.finnews.domain.mapper.DogBreedListResult
import com.nomadiq.finnews.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.finnews.domain.repository.DogBreedRepository
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * This [DogBreedRepository] utilises the remote data source (and potentially others)
 * to aggregate requested data from the [Dog Api].
 *
 */
class DogBreedRepositoryImpl @Inject constructor(
    val datasource: RemoteDataSource,
) :
    DogBreedRepository {

    override suspend fun fetchAllDogBreeds(): DogBreedListResult =
        datasource.fetchAllDogBreeds()

    override suspend fun fetchRandomImagesByDogBreed(breed: String): DogBreedRandomImageResult =
        datasource.fetchRandomImagesByDogBreed(breed = breed)
}
