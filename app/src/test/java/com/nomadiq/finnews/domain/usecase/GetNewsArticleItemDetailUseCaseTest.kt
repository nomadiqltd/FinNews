package com.nomadiq.finnews.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import utils.CoroutineTestRule

/**
 *  @author Michael Akakpo
 *
 *  Usecase test for determining correct functioning of Usecase and supporting Repository
 *
 */

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class GetNewsArticleItemDetailUseCaseTest {

    private val dataRepository = mockk<NewsArticleFeedRepository>()

    private lateinit var usecase: GetNewsArticleItemDetailUseCase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule() // for the livedata

    @get:Rule
    val mainDispatcherRule =
        CoroutineTestRule() // to replace the main dispatcher of the view model


    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `initialize then fetch news articles usecase succeeded`() = runTest {

    }

    @Test
    fun `initialize then fetch dog breeds usecase failed Empty list`() = runTest {

    }

    @Test
    fun `initialize then fetch news articles usecase failed Error`() = runTest {

    }
}