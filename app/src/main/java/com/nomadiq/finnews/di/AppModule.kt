package com.nomadiq.finnews.di

import android.content.Context
import com.nomadiq.finnews.di.NetworkModule.provideConnectivityMonitor
import com.nomadiq.finnews.di.NetworkModule.provideDogBreedRemoteDataSource
import com.nomadiq.finnews.di.NetworkModule.provideDogBreedRepository
import com.nomadiq.finnews.domain.usecase.GetDogBreedListUseCase
import com.nomadiq.finnews.domain.usecase.GetDogBreedRandomImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

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