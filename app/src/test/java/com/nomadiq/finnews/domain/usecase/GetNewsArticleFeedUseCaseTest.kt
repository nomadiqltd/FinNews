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
class GetNewsArticleFeedUseCaseTest {

    companion object {
        private const val UNKNOWN_ERROR = "Unknown Error occurred"
    }

    private val dataRepository = mockk<NewsArticleFeedRepository>()

    private lateinit var usecase: GetNewsArticleFeedUseCase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule =
        CoroutineTestRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `initialize then fetch news articles usecase succeeded`() = runTest {

    }

    @Test
    fun `initialize then fetch news articles usecase failed Empty list`() = runTest {

    }

    @Test
    fun `initialize then fetch news articles usecase failed Error`() = runTest {

    }
}