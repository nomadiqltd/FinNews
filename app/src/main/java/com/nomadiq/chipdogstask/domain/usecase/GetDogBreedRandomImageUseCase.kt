package com.nomadiq.chipdogstask.domain.usecase

import com.nomadiq.chipdogstask.data.network.connectivity.ConnectivityMonitor
import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.chipdogstask.domain.repository.DogBreedRepository
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

