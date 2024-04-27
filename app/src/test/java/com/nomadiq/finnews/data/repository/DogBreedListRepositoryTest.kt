package com.nomadiq.finnews.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.model.NewsArticleFeedItem
import com.nomadiq.finnews.domain.model.NewsArticleItemDetail
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import utils.CoroutineTestRule
import utils.TestConstants.BREED

/**
 *  @author Michael Akakpo
 *
 *  Unit tests for [DogBreedListRepository]
 *
 */


@OptIn(ExperimentalCoroutinesApi::class)
class DogBreedListRepositoryTest {

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
    fun `initialize then fetch dog breeds succeeded`() = runTest {
        // given
        val result =
            NewsArticleFeedListResult.Data(
                listOf(
                    NewsArticleFeedItem("affenpinscher"),
                    NewsArticleFeedItem("african"),
                    NewsArticleFeedItem("airedal"),
                )
            )

        // when
        coEvery {
            datasource.fetchNewsArticleFeed()
        } returns (result)

        coEvery {
            dataRepository.fetchNewsArticleFeed()
        } returns (result)

        // then
        assertThat(this is NewsArticleFeedListResult)
    }

    @Test
    fun `initialize then fetch random dog images by breed repository succeeded`() = runTest {
        // given
        val breed = BREED
        val resultListRandomImages =
            NewsArticleItemDetailResult.Data(
                NewsArticleItemDetail("https://images.dog.ceo/breeds/hound-basset/n02088238_13222.jpg"),
            )

        // when
        coEvery {
            datasource.fetchNewsArticleItemDetail(breed)
        } returns (resultListRandomImages)

        coEvery {
            dataRepository.fetchNewsArticleItemDetail(breed)
        } returns (resultListRandomImages)

        // then
        assertThat(this is NewsArticleFeedListResult)
    }
}
