package com.nomadiq.finnews.domain.repository

import com.nomadiq.finnews.domain.mapper.DogBreedListResult
import com.nomadiq.finnews.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.finnews.domain.model.DogBreed

/**
 * @author Michael Akakpo
 *
 * This interface enables access to and allows CRUD operations on [DogBreed] being passed through the domain layer (performed by the data layer)
 */
interface DogBreedRepository {
    suspend fun fetchAllDogBreeds(): DogBreedListResult
    suspend fun fetchRandomImagesByDogBreed(breed: String): DogBreedRandomImageResult
}
