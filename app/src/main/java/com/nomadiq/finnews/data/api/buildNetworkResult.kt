package com.nomadiq.finnews.data.api

import retrofit2.Response

/**
 *
 *  @author - Michael Akakpo
 *
 * Check the response from the API and return a [NetworkResult]
 * to provide a fixed predetermined set of possible outcomes on the status of the network request
 */
fun <T> buildNetworkResult(response: Response<T>): NetworkResult<T> {
    if (response.isSuccessful) {
        response.body()?.let {
            return NetworkResult.Success(it)
        } ?: return NetworkResult.Error(response.errorBody().toString())
    } else {
        return NetworkResult.Error(
            error = "errorCode :: ${response.code()} ==> ${response.message()}"
        )
    }
}