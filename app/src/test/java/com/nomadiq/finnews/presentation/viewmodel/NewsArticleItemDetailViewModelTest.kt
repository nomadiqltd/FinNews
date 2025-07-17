package com.nomadiq.finnews.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth
import com.nomadiq.finnews.data.network.connectivity.ConnectivityMonitor
import com.nomadiq.finnews.data.network.connectivity.FakeConnectivityMonitor
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository
import com.nomadiq.finnews.domain.usecase.GetNewsArticleItemDetailUseCase
import com.nomadiq.finnews.presentation.base.FinNewsApplication
import com.nomadiq.finnews.utils.TestConstants.API_URL
import com.nomadiq.finnews.utils.TestConstants.NETWORK_ERROR_MESSAGE
import com.nomadiq.finnews.utils.TestConstants.UNKNOWN_ERROR
import com.nomadiq.finnews.utils.UnconfinedDispatcherRule
import com.nomadiq.finnews.utils.article
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
 *  [NewsArticleItemDetailViewModelTest] test for determining correct functioning of Usecase and supporting Repository
 *
 */

@RunWith(RobolectricTestRunner::class)
@Config(application = FinNewsApplication::class, sdk = [34])
class NewsArticleItemDetailViewModelTest {

    private lateinit var repository: NewsArticleFeedRepository
    private lateinit var connectivityMonitor: ConnectivityMonitor
    private lateinit var usecase: GetNewsArticleItemDetailUseCase
    private lateinit var viewModel: NewsArticleItemDetailViewModel

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
        usecase = GetNewsArticleItemDetailUseCase(repository, connectivityMonitor)
        viewModel = NewsArticleItemDetailViewModel(savedStateHandle = SavedStateHandle(), usecase)
    }

    @Test
    fun `initialize viewmodel then fetch news articles item detail succeeded`() = runTest {
        //  Given
        val expectedResult = NewsArticleItemDetailResult.Success(
            article
        )

        coEvery { repository.fetchNewsArticleItemDetail(API_URL) }.returns(expectedResult)

        val result = usecase.invoke(API_URL).first()
        coVerify { repository.fetchNewsArticleItemDetail(API_URL) }

        // When
        Truth.assertThat(result).isEqualTo(NewsArticleItemDetailResult.Success(article))
        val stateData = viewModel.onDisplayNewsArticleItemDetail(API_URL).value.item

        // Then
        Truth.assertThat(stateData).isEqualTo(expectedResult.item)
        Truth.assertThat(stateData.title).isEqualTo(expectedResult.item.title)
        Truth.assertThat(stateData.body).isEqualTo(expectedResult.item.body)
        Truth.assertThat(stateData.imgUrl).isEqualTo(expectedResult.item.imgUrl)
        Truth.assertThat(stateData.date).isEqualTo(expectedResult.item.date)
    }


    @Test
    fun `initialize viewmodel then fetch news articles item detail failed Error`() = runTest {
        //  Given
        val expectedResult = NewsArticleItemDetailResult.Error(
            UNKNOWN_ERROR
        )

        coEvery { repository.fetchNewsArticleItemDetail(API_URL) }.returns(expectedResult)

        val result = usecase.invoke(API_URL).first()
        coVerify { repository.fetchNewsArticleItemDetail(API_URL) }

        // When
        Truth.assertThat(result).isEqualTo(NewsArticleItemDetailResult.Error(expectedResult.error))
        val stateData = viewModel.onDisplayNewsArticleItemDetail(API_URL).value.errorMessage

        // Then
        Truth.assertThat(stateData).isEqualTo(expectedResult.error)
    }

    @Test
    fun `initialize viewmodel then fetch news articles item detail network Error`() = runTest {
        //  Given
        val expectedResult = NewsArticleItemDetailResult.NetworkError(
            NETWORK_ERROR_MESSAGE
        )

        coEvery { repository.fetchNewsArticleItemDetail(API_URL) }.returns(expectedResult)

        val result = usecase.invoke(API_URL).first()
        coVerify { repository.fetchNewsArticleItemDetail(API_URL) }

        // When
        Truth.assertThat(result)
            .isEqualTo(NewsArticleItemDetailResult.NetworkError(expectedResult.message))
        val stateData = viewModel.onDisplayNewsArticleItemDetail(API_URL).value.errorMessage

        // Then
        Truth.assertThat(stateData).isEqualTo(expectedResult.message)
    }

}