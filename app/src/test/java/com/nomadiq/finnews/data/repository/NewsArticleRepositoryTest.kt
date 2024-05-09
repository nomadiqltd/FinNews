package com.nomadiq.finnews.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.utils.CoroutineTestRule
import com.nomadiq.finnews.utils.TestConstants.API_URL
import com.nomadiq.finnews.utils.TestConstants.BODY
import com.nomadiq.finnews.utils.TestConstants.IMG_URL
import com.nomadiq.finnews.utils.TestConstants.NETWORK_ERROR_MESSAGE
import com.nomadiq.finnews.utils.TestConstants.SUBTITLE
import com.nomadiq.finnews.utils.TestConstants.SUBTITLE_TWO
import com.nomadiq.finnews.utils.TestConstants.TITLE
import com.nomadiq.finnews.utils.TestConstants.TITLE_TWO
import com.nomadiq.finnews.utils.TestConstants.UNKNOWN_ERROR
import com.nomadiq.finnews.utils.article
import com.nomadiq.finnews.utils.listOfArticles
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 *  @author Michael Akakpo
 *
 *  Unit tests for [DogBreedListRepository]
 *
 */


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class NewsArticleRepositoryTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: NewsArticleFeedRepositoryImpl
    private lateinit var datasource: FakeRemoteDataSource

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule =
        CoroutineTestRule()


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        datasource = mockk<FakeRemoteDataSource>()
        repository =
            NewsArticleFeedRepositoryImpl(datasource = datasource, ioDispatcher = testDispatcher)
    }

    @Test
    fun `initialize then fetch news articles repository succeeded`() = runTest(testDispatcher) {
        //  Given
        val expectedResult = NewsArticleFeedListResult.Success(
            listOfArticles
        )

        coEvery { datasource.fetchNewsArticleFeed() } returns expectedResult

        // When
        val result = repository.fetchNewsArticleFeed()

        // Then
        coVerify { datasource.fetchNewsArticleFeed() }

        assertThat(result).isEqualTo(expectedResult)
        assertThat(expectedResult.itemsList.size).isEqualTo(2)
        assertThat(expectedResult.itemsList.first().title).isEqualTo(TITLE)
        assertThat(expectedResult.itemsList.first().subtitle).isEqualTo(SUBTITLE)
        assertThat(expectedResult.itemsList.first().imgUrl).isEqualTo(IMG_URL)
        assertThat(expectedResult.itemsList.first().apiUrl).isEqualTo(API_URL)
    }

    @Test
    fun `initialize then fetch news articles repository error`() = runTest(testDispatcher) {
        //  Given
        val expectedResult = NewsArticleFeedListResult.Error(UNKNOWN_ERROR)
        coEvery {
            datasource.onErrorTriggered(true)
            datasource.fetchNewsArticleFeed()
        } returns expectedResult

        // When
        val result = repository.fetchNewsArticleFeed()

        // Then
        coVerify {
            datasource.fetchNewsArticleFeed()
        }
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `initialize then fetch news articles repository network error`() = runTest(testDispatcher) {
        //  Given
        val expectedResult = NewsArticleFeedListResult.NetworkError(NETWORK_ERROR_MESSAGE)
        coEvery {
            datasource.fetchNewsArticleFeed()
        } returns expectedResult

        // When
        val result = repository.fetchNewsArticleFeed()

        // Then
        coVerify {
            datasource.fetchNewsArticleFeed()
        }
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `open news article item detail succeeded`() = runTest(testDispatcher) {
        //  Given
        val expectedResult = NewsArticleItemDetailResult.Success(
            article
        )

        coEvery { datasource.fetchNewsArticleItemDetail(API_URL) } returns expectedResult

        // When
        val result = repository.fetchNewsArticleItemDetail(API_URL)

        // Then
        coVerify { datasource.fetchNewsArticleItemDetail(API_URL) }

        assertThat(result).isEqualTo(expectedResult)
        assertThat(expectedResult.item.title).isEqualTo(TITLE_TWO)
        assertThat(expectedResult.item.date).isEqualTo(SUBTITLE_TWO)
        assertThat(expectedResult.item.imgUrl).isEqualTo(IMG_URL)
        assertThat(expectedResult.item.body).isEqualTo(BODY)
    }

    @Test
    fun `open news article item detail error`() = runTest(testDispatcher) {
        //  Given
        val expectedResult = NewsArticleItemDetailResult.Error(UNKNOWN_ERROR)

        coEvery { datasource.fetchNewsArticleItemDetail(API_URL) } returns expectedResult

        // When
        val result = repository.fetchNewsArticleItemDetail(API_URL)

        // Then
        coVerify { datasource.fetchNewsArticleItemDetail(API_URL) }

        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `open news article item detail network error`() = runTest(testDispatcher) {
        //  Given
        val expectedResult = NewsArticleItemDetailResult.Error(NETWORK_ERROR_MESSAGE)

        coEvery { datasource.fetchNewsArticleItemDetail(API_URL) } returns expectedResult

        // When
        val result = repository.fetchNewsArticleItemDetail(API_URL)

        // Then
        coVerify { datasource.fetchNewsArticleItemDetail(API_URL) }

        assertThat(result).isEqualTo(expectedResult)
    }
}