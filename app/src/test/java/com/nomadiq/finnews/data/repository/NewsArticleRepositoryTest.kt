package com.nomadiq.finnews.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import utils.CoroutineTestRule

/**
 *  @author Michael Akakpo
 *
 *  Unit tests for [DogBreedListRepository]
 *
 */


@OptIn(ExperimentalCoroutinesApi::class)
class NewsArticleRepositoryTest {

    private lateinit var datasource: RemoteDataSource

    private lateinit var dataRepository: NewsArticleFeedRepositoryImpl

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule =
        CoroutineTestRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        datasource = mockk<FakeRemoteDataSource>()
        dataRepository = NewsArticleFeedRepositoryImpl(datasource = datasource)
    }

    @Test
    fun `initialize then fetch news articles repository succeeded`() = runTest {

    }

    @Test
    fun `initialize then fetch news articles repository error`() = runTest {

    }
}
