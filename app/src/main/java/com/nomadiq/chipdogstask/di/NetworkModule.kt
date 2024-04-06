package com.nomadiq.chipdogstask.di

import android.content.Context
import com.nomadiq.chipdogstask.data.api.ApiService
import com.nomadiq.chipdogstask.data.network.connectivity.ConnectivityMonitor
import com.nomadiq.chipdogstask.data.network.connectivity.ConnectivityMonitorImpl
import com.nomadiq.chipdogstask.data.repository.DogBreedRemoteDataSourceImpl
import com.nomadiq.chipdogstask.data.repository.DogBreedRepositoryImpl
import com.nomadiq.chipdogstask.data.repository.RemoteDataSource
import com.nomadiq.chipdogstask.domain.repository.DogBreedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideContext(): ApplicationContext = ApplicationContext()

    @Singleton
    @Provides
    fun provideConnectivityMonitor(@ApplicationContext appContext: Context): ConnectivityMonitor =
        ConnectivityMonitorImpl(appContext)

    @Provides
    @Singleton
    fun provideApiService(
        @ApplicationContext context: Context,
        connectivityMonitor: ConnectivityMonitor
    ): ApiService {
        return ApiService(
            context = context,
            connectivityMonitor = connectivityMonitor,
        )
    }

    @Singleton
    @Provides
    fun provideDogBreedRemoteDataSource(
        @ApplicationContext applicationContext: Context,
        connectivityMonitor: ConnectivityMonitor,
    ): RemoteDataSource =
        DogBreedRemoteDataSourceImpl(
            context = applicationContext,
            connectivityMonitor = connectivityMonitor,
            providedApiService = provideApiService(
                context = applicationContext,
                connectivityMonitor = connectivityMonitor
            )
        )


    @Singleton
    @Provides
    fun provideDogBreedRepository(dogBreedDataSource: RemoteDataSource): DogBreedRepository =
        DogBreedRepositoryImpl(dogBreedDataSource)
}