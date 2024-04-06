package com.nomadiq.finnews.domain.usecase

import com.nomadiq.finnews.data.network.connectivity.ConnectivityMonitor
import com.nomadiq.finnews.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.finnews.domain.repository.DogBreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * Use case for fetching a list of [@DogBreedImageDetail] from user selecting a particular breed within presentation layer
 *
 */

class GetDogBreedRandomImageUseCase @Inject constructor(
    private val dogBreedRepository: DogBreedRepository,
    private val connectivityMonitor: ConnectivityMonitor,
) {

    suspend operator fun invoke(breed: String): Flow<DogBreedRandomImageResult> {
        return if (connectivityMonitor.isConnected()) {
            val dogBreedRandomImageResult =
                dogBreedRepository.fetchRandomImagesByDogBreed(breed = breed)
            flow { emit(dogBreedRandomImageResult) }
        } else {
            flowOf(DogBreedRandomImageResult.NetworkError)
        }
    }
}

