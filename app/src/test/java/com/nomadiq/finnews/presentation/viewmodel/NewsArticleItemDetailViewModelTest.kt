package com.nomadiq.finnews.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.model.NewsArticleItemDetail
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository
import com.nomadiq.finnews.domain.usecase.GetNewsArticleItemDetailUseCase
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import utils.CoroutineTestRule
import utils.TestConstants.BREED
import utils.TestConstants.UNKNOWN_ERROR

@ExperimentalCoroutinesApi
@OptIn(ExperimentalCoroutinesApi::class)
class NewsArticleItemDetailViewModelTest {

    private lateinit var viewModel: NewsArticleItemDetailViewModel

    private val dataRepository = mockk<NewsArticleFeedRepository>()

    private val usecase = mockk<GetNewsArticleItemDetailUseCase>()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule() // for the livedata

    @get:Rule
    val mainDispatcherRule =
        CoroutineTestRule() // to replace the main dispatcher of the view model

    @Before
    fun setUp() {
   //     viewModel = NewsArticleItemDetailViewModel(savedStateHandle = SavedStateHandle())
    }

    @Test
    fun `initialize then fetch dog breeds viewmodel succeeded`() = runTest {
        // given
        val breed = BREED
        val result =
            NewsArticleItemDetailResult.Data(
           //     item =
                 TODO()
           //     listOf()
            )

        // when
        coEvery { dataRepository.fetchNewsArticleItemDetail(apiUrl = breed) } returns (result)
        coEvery { usecase.invoke(apiUrl = BREED) } returns flow {
            emit(result)

            // then
         //   viewModel.uiState.value.item.size shouldBe 10
        }
    }

    @Test
    fun `initialize then fetch dog breeds viewmodel failed Empty list`() = runTest {
        // given
        val breed = BREED
//        val resultEmptyList =
//            NewsArticleItemDetailResult.Empty
//
//        // when
//        coEvery { dataRepository.fetchNewsArticleItemDetail(breed = breed) } returns (resultEmptyList)
//        coEvery { usecase.invoke(breed = breed) } returns flow {
//            emit(resultEmptyList)
//
//            // then
//            viewModel.uiState.value.item.size shouldBe 0
//        }
    }


    @Test
    fun `initialize then fetch dog breeds viewmodel failed Error`() = runTest {
        // given
        val breed = BREED
        val result =
            NewsArticleItemDetailResult.Error(error = UNKNOWN_ERROR)

        // when
//        coEvery { dataRepository.fetchNewsArticleItemDetail(breed = breed) } returns (result)
//        coEvery { usecase.invoke(breed = breed) } returns flow {
//            emit(result)
//
//            // then
//            viewModel.uiState.value.item.size shouldBe 0
//        }
    }
}