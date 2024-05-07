package com.nomadiq.finnews.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nomadiq.finnews.data.repository.FakeNewsArticleFeedRepositoryImpl
import com.nomadiq.finnews.domain.usecase.GetNewsArticleFeedUseCase
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import utils.CoroutineTestRule

@ExperimentalCoroutinesApi
@OptIn(ExperimentalCoroutinesApi::class)
class NewsArticleFeedViewModelTest {

    private val dataRepository = mockk<FakeNewsArticleFeedRepositoryImpl>(relaxed = true)
    private val usecase = mockk<GetNewsArticleFeedUseCase>(relaxed = true)

    private val viewModel: NewsArticleFeedViewModel by lazy {
        NewsArticleFeedViewModel(usecase)
    }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule() // for the livedata

    @get:Rule
    val mainDispatcherRule =
        CoroutineTestRule()


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
    }

    @Test
    fun `initialize then get news articles viewmodel succeeded`() = runTest {

    }

    @Test
    fun `initialize then get news articles viewmodel failed Error`() = runTest {

    }
}