package com.nomadiq.finnews.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nomadiq.finnews.domain.mapper.NewsArticleFeedListResult
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import utils.CoroutineTestRule
import kotlin.test.assertTrue

/**
 *  @author Michael Akakpo
 *
 *  Usecase test for determining correct functioning of Usecase and supporting Repository
 *
 */


@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class DogBreedListUseCaseTest {

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
      //  usecase = GetNewsArticleFeedUseCase("")
    }

    @Test
    fun `initialize then fetch dog breeds usecase succeeded`() = runTest {
        // given
        val result =
            NewsArticleFeedListResult.Data(
                listOf(
                )
            )


        // when
        coEvery { dataRepository.fetchNewsArticleFeed() } returns (result)
        coEvery { usecase.invoke() } returns flow {
            emit(result)

            // then
            result shouldBe 6
            assertTrue(this is NewsArticleFeedListResult)
        }
    }

    @Test
    fun `initialize then fetch dog breeds usecase failed Empty list`() = runTest {
        // given
        coEvery { dataRepository.fetchNewsArticleFeed() }.returns(NewsArticleFeedListResult.Empty)
        coEvery {
            usecase.invoke().first()
        } returns NewsArticleFeedListResult.Empty

        // then
        assertThat(this is NewsArticleFeedListResult)
    }

    @Test
    fun `initialize then fetch dog breeds usecase failed Error`() = runTest {
        // given
        coEvery { dataRepository.fetchNewsArticleFeed() }.returns(NewsArticleFeedListResult.Error(error = UNKNOWN_ERROR))
        coEvery { usecase.invoke() } returns flow {
            emit(NewsArticleFeedListResult.Error(error = UNKNOWN_ERROR))
        }

        // then
        assertThat(this is NewsArticleFeedListResult)
    }
}