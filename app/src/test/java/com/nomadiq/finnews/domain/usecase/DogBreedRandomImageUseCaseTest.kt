package com.nomadiq.finnews.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nomadiq.finnews.domain.mapper.NewsArticleItemDetailResult
import com.nomadiq.finnews.domain.model.NewsArticleItemDetail
import com.nomadiq.finnews.domain.repository.NewsArticleFeedRepository
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
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import utils.CoroutineTestRule
import utils.TestConstants.BREED
import kotlin.test.assertTrue

/**
 *  @author Michael Akakpo
 *
 *  Usecase test for determining correct functioning of Usecase and supporting Repository
 *
 */

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class DogBreedRandomImageUseCaseTest {

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
     //   usecase = GetNewsArticleItemDetailUseCase(newsArticleFeedRepository = dataRepository)
    }

    @Test
    fun `initialize then fetch dog breeds usecase succeeded`() = runTest {
        // given
        val breed = BREED
        val result =
            NewsArticleItemDetailResult.Data(
                    NewsArticleItemDetail("https://images.dog.ceo/breeds/hound-afghan/n02088094_251.jpg"),
            )

        // when
        coEvery { dataRepository.fetchNewsArticleItemDetail(breed) } returns (result)
        coEvery { usecase.invoke(breed) } returns flow {
            emit(result)

            // then
            result shouldBe 5
            assertTrue(this is NewsArticleItemDetailResult)
        }
    }

    @Test
    fun `initialize then fetch dog breeds usecase failed Empty list`() = runTest {
        // given
        val breed = BREED
        ///val result = NewsArticleItemDetailResult.Error

        // when
    //    coEvery { dataRepository.fetchNewsArticleItemDetail(breed) } returns (result)
        coEvery { usecase.invoke(BREED) } returns flow {
   //         emit(result)

            // then
            assertThat(this is NewsArticleItemDetailResult)
        }
    }

    @Test
    fun `initialize then fetch dog breeds usecase failed Error`() = runTest {
        // given
        val breed = BREED
        val result = NewsArticleItemDetailResult.Error("")

        coEvery { dataRepository.fetchNewsArticleItemDetail(breed) } returns (result)
        coEvery { usecase.invoke(breed) } returns flow {
            emit(result)

            // then
            assertTrue(this is NewsArticleItemDetailResult)
        }
    }
}