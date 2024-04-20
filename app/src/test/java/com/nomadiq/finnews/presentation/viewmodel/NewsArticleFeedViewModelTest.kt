package com.nomadiq.finnews.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nomadiq.finnews.data.repository.FakeNewsArticleFeedRepositoryImpl
import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.model.DogBreed
import com.nomadiq.finnews.domain.usecase.GetNewsArticleFeedUseCase
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import utils.CoroutineTestRule
import utils.TestConstants.EMPTY_ERROR
import utils.TestConstants.UNKNOWN_ERROR

@ExperimentalCoroutinesApi
@OptIn(ExperimentalCoroutinesApi::class)
class NewsArticleFeedViewModelTest {

    private lateinit var viewModel: NewsArticleFeedViewModel

    private val dataRepository = mockk<FakeNewsArticleFeedRepositoryImpl>()

    private val usecase = mockk<GetNewsArticleFeedUseCase>()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule() // for the livedata

    @get:Rule
    val mainDispatcherRule =
        CoroutineTestRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        viewModel = NewsArticleFeedViewModel(usecase)
    }

    @Test
    fun `initialize then fetch dog breeds viewmodel succeeded`() = runTest {
        // given
        var itemCount = 0
        val result =
            NewsArticleFeedListResult.Data(
                listOf(
                )
            )

        // when
        coEvery { dataRepository.fetchNewsArticleFeed() } returns (result)
        coEvery { usecase.invoke() } returns flow {
            emit(result)

            itemCount = viewModel.uiState.value.items.size
            viewModel.uiState.value.items.size shouldBe 4
        }

        // then
        assertThat(itemCount == 4)
    }

//    @Test
//    fun `initialize then fetch dog breeds viewmodel failed Empty list`() = runTest {
//        // given
//        coEvery { dataRepository.fetchNewsArticleFeed() } returns (NewsArticleFeedListResult)
//        coEvery { usecase.invoke() } returns flow {
//            emit(NewsArticleFeedListResult.Empty)
//
//            // then
//            viewModel.uiState.value.items.size shouldBe 0
//            viewModel.uiState.value.errorMessage shouldBe EMPTY_ERROR
//        }
//    }


    @Test
    fun `initialize then fetch dog breeds viewmodel failed Error`() = runTest {
        // given
        coEvery { dataRepository.fetchNewsArticleFeed() } returns (NewsArticleFeedListResult.Error(error = UNKNOWN_ERROR))
        coEvery { usecase.invoke() } returns flow {
            emit(NewsArticleFeedListResult.Error(error = UNKNOWN_ERROR))
        }

            // then
            viewModel.uiState.value.items.size shouldBe 0
          //  viewModel.uiState.value.errorMessage shouldBe UNKNOWN_ERROR
    }
}