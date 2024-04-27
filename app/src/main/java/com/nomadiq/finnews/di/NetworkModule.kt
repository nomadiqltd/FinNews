package com.nomadiq.finnews.di

import android.content.Context
import com.nomadiq.finnews.data.api.ApiService
import com.nomadiq.finnews.data.network.connectivity.ConnectivityMonitor
import com.nomadiq.finnews.data.network.connectivity.ConnectivityMonitorImpl
import com.nomadiq.finnews.data.repository.NewsArticleRemoteDataSourceImpl
import com.nomadiq.finnews.data.repository.NewsArticleFeedRepositoryImpl
import com.nomadiq.finnews.data.repository.RemoteDataSource
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository
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
    fun provideNewsArticleRemoteDataSource(
        @ApplicationContext applicationContext: Context,
        connectivityMonitor: ConnectivityMonitor,
    ): RemoteDataSource =
        NewsArticleRemoteDataSourceImpl(
            context = applicationContext,
            connectivityMonitor = connectivityMonitor,
            providedApiService = provideApiService(
                context = applicationContext,
                connectivityMonitor = connectivityMonitor
            )
        )


    @Singleton
    @Provides
    fun provideNewsArticleFeedRepository(remoteDataSource: RemoteDataSource): NewsArticleFeedRepository =
        NewsArticleFeedRepositoryImpl(remoteDataSource)
}