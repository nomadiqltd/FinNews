package com.nomadiq.finnews.data.api

import com.nomadiq.finnews.data.model.DogBreedApiResponse
import com.nomadiq.finnews.data.model.DogBreedRandomImagesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author - Michael Akakpo
 *
 * A public interface that exposes the [Dog Ceo Api] to the client
 *
 */
interface DogBreedApi {
    @GET("breed/{breed}/images/random/10")
    suspend fun fetchRandomImagesByDogBreed(@Path("breed") breed: String): Response<DogBreedRandomImagesResponse>

    @GET("breeds/list/all")
    suspend fun fetchAllDogBreeds(): Response<DogBreedApiResponse>
}
