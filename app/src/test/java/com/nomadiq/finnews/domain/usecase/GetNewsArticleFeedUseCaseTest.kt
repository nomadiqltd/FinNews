package com.nomadiq.finnews.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.nomadiq.finnews.data.network.connectivity.ConnectivityMonitor
import com.nomadiq.finnews.data.network.connectivity.FakeConnectivityMonitor
import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository
import com.nomadiq.finnews.presentation.base.FinNewsApplication
import com.nomadiq.finnews.utils.CoroutineTestRule
import com.nomadiq.finnews.utils.TestConstants
import com.nomadiq.finnews.utils.TestConstants.NETWORK_ERROR_MESSAGE
import com.nomadiq.finnews.utils.TestConstants.UNKNOWN_ERROR
import com.nomadiq.finnews.utils.listOfArticles
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import kotlin.intArrayOf

/**
 *  @author Michael Akakpo
 *
 *  Usecase test for determining correct functioning of Usecase and supporting Repository
 *
 */


@RunWith(AndroidJUnit4::class)
@Config(application = FinNewsApplication::class, sdk = [34])
@ExperimentalCoroutinesApi
class GetNewsArticleFeedUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: NewsArticleFeedRepository
    private lateinit var connectivityMonitor: ConnectivityMonitor
    private lateinit var usecase: GetNewsArticleFeedUseCase

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
        usecase = GetNewsArticleFeedUseCase(repository, connectivityMonitor)
    }

    @Test
    fun `initialize then fetch news articles usecase succeeded`() = runTest(testDispatcher) {
        //  Given
        val expectedResult = NewsArticleFeedListResult.Success(
            listOfArticles
        )
        coEvery { repository.fetchNewsArticleFeed() }.returns(expectedResult)

        // when
        val result = usecase.invoke().first()
        coVerify { repository.fetchNewsArticleFeed() }

        // then
        Truth.assertThat(result).isEqualTo(expectedResult)
        Truth.assertThat(expectedResult.itemsList.size).isEqualTo(2)
        Truth.assertThat(expectedResult.itemsList.first().title).isEqualTo(TestConstants.TITLE)
        Truth.assertThat(expectedResult.itemsList.first().subtitle)
            .isEqualTo(TestConstants.SUBTITLE)
        Truth.assertThat(expectedResult.itemsList.first().imgUrl).isEqualTo(TestConstants.IMG_URL)
        Truth.assertThat(expectedResult.itemsList.first().apiUrl).isEqualTo(TestConstants.API_URL)
    }

    @Test
    fun `initialize then fetch news articles usecase Error`() = runTest(testDispatcher) {
        //  Given
        val expectedResult = NewsArticleFeedListResult.Error(
            UNKNOWN_ERROR
        )
        coEvery { repository.fetchNewsArticleFeed() }.returns(expectedResult)

        // when
        val result = usecase.invoke().first()
        coVerify { repository.fetchNewsArticleFeed() }

        // then
        Truth.assertThat(result).isEqualTo(expectedResult)
    }


    @Test
    fun `initialize then fetch news articles usecase failed network Error`() = runTest(testDispatcher) {
        //  Given
        val expectedResult = NewsArticleFeedListResult.NetworkError(
            NETWORK_ERROR_MESSAGE
        )
        coEvery { repository.fetchNewsArticleFeed() }.returns(expectedResult)

        // when
        val result = usecase.invoke().first()
        coVerify { repository.fetchNewsArticleFeed() }

        // then
        Truth.assertThat(result).isEqualTo(expectedResult)
    }
}