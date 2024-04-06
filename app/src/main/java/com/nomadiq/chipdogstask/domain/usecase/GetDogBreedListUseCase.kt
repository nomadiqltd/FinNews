package com.nomadiq.chipdogstask.domain.usecase

import com.nomadiq.chipdogstask.data.network.connectivity.ConnectivityMonitor
import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.domain.repository.DogBreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * Use case for loading a list of [@DogBreed] for user to view within a list
 */

class GetDogBreedListUseCase @Inject constructor(
    private val dogBreedRepository: DogBreedRepository,
    private val connectivityMonitor: ConnectivityMonitor,
) {

    suspend operator fun invoke(): Flow<DogBreedListResult> {
        return if (connectivityMonitor.isConnected()) {
            val dogBreedListResult = dogBreedRepository.fetchAllDogBreeds()
            flow { emit(dogBreedListResult) }
        } else {
            flowOf(DogBreedListResult.NetworkError)
        }
    }
}

