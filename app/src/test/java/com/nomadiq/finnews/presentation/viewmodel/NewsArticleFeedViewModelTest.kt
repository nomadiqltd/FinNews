package com.nomadiq.finnews.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.nomadiq.finnews.data.network.connectivity.ConnectivityMonitor
import com.nomadiq.finnews.data.network.connectivity.FakeConnectivityMonitor
import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository
import com.nomadiq.finnews.domain.usecase.GetNewsArticleFeedUseCase
import com.nomadiq.finnews.presentation.base.FinNewsApplication
import com.nomadiq.finnews.utils.TestConstants.NETWORK_ERROR_MESSAGE
import com.nomadiq.finnews.utils.TestConstants.UNKNOWN_ERROR
import com.nomadiq.finnews.utils.UnconfinedDispatcherRule
import com.nomadiq.finnews.utils.listOfArticles
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 *  @author Michael Akakpo
 *
 *  [NewsArticleFeedViewModelTest] test for determining correct functioning of ViewModel and supporting usecase
 *
 */

@RunWith(RobolectricTestRunner::class)
@Config(application = FinNewsApplication::class, sdk = [32])
class NewsArticleFeedViewModelTest {

    private lateinit var repository: NewsArticleFeedRepository
    private lateinit var connectivityMonitor: ConnectivityMonitor
    private lateinit var usecase: GetNewsArticleFeedUseCase
    private lateinit var viewModel: NewsArticleFeedViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule =
        UnconfinedDispatcherRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        connectivityMonitor = FakeConnectivityMonitor()
        repository = mockk<NewsArticleFeedRepository>(relaxed = true)
        usecase = GetNewsArticleFeedUseCase(repository, connectivityMonitor)
        viewModel = NewsArticleFeedViewModel(usecase)
    }

    @Test
    fun `initialize then get news articles viewmodel succeeded`() = runTest() {
        //  Given
        val expectedResult = NewsArticleFeedListResult.Success(
            listOfArticles
        )

        coEvery { repository.fetchNewsArticleFeed() }.returns(expectedResult)

        val result = usecase.invoke().first()
        coVerify { repository.fetchNewsArticleFeed() }

        // When
        Truth.assertThat(result).isEqualTo(NewsArticleFeedListResult.Success(listOfArticles))
        val stateData = viewModel.onDisplayNewsArticleFeedList().value.items.first()

        // Then
        Truth.assertThat(stateData).isEqualTo(expectedResult.itemsList.first())
        Truth.assertThat(stateData.title).isEqualTo(expectedResult.itemsList.first().title)
        Truth.assertThat(stateData.subtitle).isEqualTo(expectedResult.itemsList.first().subtitle)
        Truth.assertThat(stateData.imgUrl).isEqualTo(expectedResult.itemsList.first().imgUrl)
        Truth.assertThat(stateData.apiUrl).isEqualTo(expectedResult.itemsList.first().apiUrl)
    }

    @Test
    fun `initialize then get news articles viewmodel failed Error`() = runTest() {
        //  Given
        val expectedResult = NewsArticleFeedListResult.Error(
            UNKNOWN_ERROR
        )

        coEvery { repository.fetchNewsArticleFeed() }.returns(expectedResult)

        val result = usecase.invoke().first()
        coVerify { repository.fetchNewsArticleFeed() }

        // When
        Truth.assertThat(result).isEqualTo(NewsArticleFeedListResult.Error(UNKNOWN_ERROR))
        val stateData = viewModel.onDisplayNewsArticleFeedList().value.errorMessage

        // Then
        Truth.assertThat(stateData).isEqualTo(expectedResult.error)
    }

    @Test
    fun `initialize then get news articles viewmodel network Error`() = runTest() {
        //  Given
        val expectedResult = NewsArticleFeedListResult.NetworkError(
            NETWORK_ERROR_MESSAGE
        )

        coEvery { repository.fetchNewsArticleFeed() }.returns(expectedResult)

        val result = usecase.invoke().first()
        coVerify { repository.fetchNewsArticleFeed() }

        // When
        Truth.assertThat(result).isEqualTo(
            NewsArticleFeedListResult.NetworkError(
                NETWORK_ERROR_MESSAGE
            )
        )
        val stateData = viewModel.onDisplayNewsArticleFeedList().value.errorMessage

        // Then
        Truth.assertThat(stateData).isEqualTo(expectedResult.message)
    }
}