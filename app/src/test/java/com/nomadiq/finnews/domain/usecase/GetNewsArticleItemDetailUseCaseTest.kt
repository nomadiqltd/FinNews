package com.nomadiq.finnews.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.nomadiq.finnews.data.network.connectivity.ConnectivityMonitor
import com.nomadiq.finnews.data.network.connectivity.FakeConnectivityMonitor
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository
import com.nomadiq.finnews.utils.CoroutineTestRule
import com.nomadiq.finnews.utils.TestConstants
import com.nomadiq.finnews.utils.TestConstants.API_URL
import com.nomadiq.finnews.utils.TestConstants.NETWORK_ERROR_MESSAGE
import com.nomadiq.finnews.utils.TestConstants.UNKNOWN_ERROR
import com.nomadiq.finnews.utils.article
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 *  @author Michael Akakpo
 *
 *  Usecase test for determining correct functioning of Usecase and supporting Repository
 *
 */

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class GetNewsArticleItemDetailUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: NewsArticleFeedRepository
    private lateinit var connectivityMonitor: ConnectivityMonitor
    private lateinit var usecase: GetNewsArticleItemDetailUseCase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule =
        CoroutineTestRule()


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        connectivityMonitor = FakeConnectivityMonitor()
        repository = mockk<NewsArticleFeedRepository>(relaxed = true)
        usecase = GetNewsArticleItemDetailUseCase(repository, connectivityMonitor)
    }

    @Test
    fun `open fetched news item detail usecase succeeded`() = runTest(testDispatcher) {
        //  Given
        val expectedResult = NewsArticleItemDetailResult.Success(
            article
        )
        coEvery { repository.fetchNewsArticleItemDetail(API_URL) }.returns(expectedResult)

        // when
        val result = usecase.invoke(API_URL).first()
        coVerify { repository.fetchNewsArticleItemDetail(API_URL) }

        // then
        Truth.assertThat(result).isEqualTo(expectedResult)
        Truth.assertThat(expectedResult.item.title).isEqualTo(TestConstants.TITLE_TWO)
        Truth.assertThat(expectedResult.item.date).isEqualTo(TestConstants.SUBTITLE_TWO)
        Truth.assertThat(expectedResult.item.imgUrl).isEqualTo(TestConstants.IMG_URL)
        Truth.assertThat(expectedResult.item.body).isEqualTo(TestConstants.BODY)
    }

    @Test
    fun `open fetched news item detail usecase failed error`() = runTest(testDispatcher) {
        //  Given
        val expectedResult = NewsArticleItemDetailResult.Error(
            UNKNOWN_ERROR
        )
        coEvery { repository.fetchNewsArticleItemDetail(API_URL) }.returns(expectedResult)

        // when
        val result = usecase.invoke(API_URL).first()
        coVerify { repository.fetchNewsArticleItemDetail(API_URL) }

        // then
        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `open fetched news item detail usecase network Error`() = runTest(testDispatcher) {
        //  Given
        val expectedResult = NewsArticleItemDetailResult.NetworkError(
            NETWORK_ERROR_MESSAGE
        )
        coEvery { repository.fetchNewsArticleItemDetail(API_URL) }.returns(expectedResult)

        // when
        val result = usecase.invoke(API_URL).first()
        coVerify { repository.fetchNewsArticleItemDetail(API_URL) }

        // then
        Truth.assertThat(result).isEqualTo(expectedResult)
    }
}