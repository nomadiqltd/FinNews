package com.nomadiq.finnews.di

import android.content.Context
import com.nomadiq.finnews.di.NetworkModule.provideConnectivityMonitor
import com.nomadiq.finnews.di.NetworkModule.provideNewsArticleRemoteDataSource
import com.nomadiq.finnews.di.NetworkModule.provideNewsArticleFeedRepository
import com.nomadiq.finnews.domain.usecase.GetNewsArticleFeedUseCase
import com.nomadiq.finnews.domain.usecase.GetNewsArticleItemDetailUseCase
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
    fun provideGetNewsArticleFeedUseCase(@ApplicationContext context: Context): GetNewsArticleFeedUseCase =
        GetNewsArticleFeedUseCase(
            connectivityMonitor = provideConnectivityMonitor(context),
            newsArticleFeedRepository = provideNewsArticleFeedRepository(
                remoteDataSource = provideNewsArticleRemoteDataSource(
                    applicationContext = context,
                    connectivityMonitor = provideConnectivityMonitor(context)
                )
            )
        )

    @Provides
    fun provideGetNewsArticleItemDetailUseCase(@ApplicationContext context: Context): GetNewsArticleItemDetailUseCase =
        GetNewsArticleItemDetailUseCase(
            connectivityMonitor = provideConnectivityMonitor(context),
            newsArticleFeedRepository = provideNewsArticleFeedRepository(
                remoteDataSource = provideNewsArticleRemoteDataSource(
                    applicationContext = context,
                    connectivityMonitor = provideConnectivityMonitor(context)
                )
            )
        )
}