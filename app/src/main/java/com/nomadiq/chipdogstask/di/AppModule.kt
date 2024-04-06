package com.nomadiq.chipdogstask.di

import android.content.Context
import com.nomadiq.chipdogstask.data.repository.DogBreedRepositoryImpl
import com.nomadiq.chipdogstask.data.repository.RemoteDataSource
import com.nomadiq.chipdogstask.di.NetworkModule.provideConnectivityMonitor
import com.nomadiq.chipdogstask.di.NetworkModule.provideContext
import com.nomadiq.chipdogstask.di.NetworkModule.provideDogBreedRemoteDataSource
import com.nomadiq.chipdogstask.di.NetworkModule.provideDogBreedRepository
import com.nomadiq.chipdogstask.domain.repository.DogBreedRepository
import com.nomadiq.chipdogstask.domain.usecase.GetDogBreedListUseCase
import com.nomadiq.chipdogstask.domain.usecase.GetDogBreedRandomImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideGetDogBreedListUseCase(@ApplicationContext context: Context): GetDogBreedListUseCase =
        GetDogBreedListUseCase(
            connectivityMonitor = provideConnectivityMonitor(context),
            dogBreedRepository = provideDogBreedRepository(
                dogBreedDataSource = provideDogBreedRemoteDataSource(
                    applicationContext = context,
                    connectivityMonitor = provideConnectivityMonitor(context)
                )
            )
        )

    @Provides
    fun provideGetDogBreedRandomImageUseCase(@ApplicationContext context: Context): GetDogBreedRandomImageUseCase =
        GetDogBreedRandomImageUseCase(
            connectivityMonitor = provideConnectivityMonitor(context),
            dogBreedRepository = provideDogBreedRepository(
                dogBreedDataSource = provideDogBreedRemoteDataSource(
                    applicationContext = context,
                    connectivityMonitor = provideConnectivityMonitor(context)
                )
            )
        )
}