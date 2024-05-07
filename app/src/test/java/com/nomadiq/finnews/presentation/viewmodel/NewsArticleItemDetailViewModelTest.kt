package com.nomadiq.finnews.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository
import com.nomadiq.finnews.domain.usecase.GetNewsArticleItemDetailUseCase
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import utils.CoroutineTestRule

@ExperimentalCoroutinesApi
@OptIn(ExperimentalCoroutinesApi::class)
class NewsArticleItemDetailViewModelTest {

    private val usecase = mockk<GetNewsArticleItemDetailUseCase>()

    private val dataRepository = mockk<NewsArticleFeedRepository>()

    private lateinit var viewModel: NewsArticleItemDetailViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule() // for the livedata

    @get:Rule
    val mainDispatcherRule =
        CoroutineTestRule() // to replace the main dispatcher of the view model

    @Before
    fun setUp() {
        viewModel = NewsArticleItemDetailViewModel(savedStateHandle = SavedStateHandle(), usecase)
    }

    @Test
    fun `initialize then fetch news articles item detail viewmodel succeeded`() = runTest {

    }


    @Test
    fun `initialize then fetch news articles item detail viewmodel failed Error`() = runTest {

    }
}