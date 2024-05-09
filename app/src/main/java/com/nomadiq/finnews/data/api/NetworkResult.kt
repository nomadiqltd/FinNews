package com.nomadiq.finnews.data.api

/**
 * @author Michael Akakpo
 *
 * A generic class that contains data and status about incoming API response data
 *
 * [Success] - Success 200 range responses
 * [Error] - General error capture
 * [NetworkError] - Network, connectivity issues
 */

/** A sealed class representing preset [NetworkResult] types to facilitate error management **/
sealed class NetworkResult<T> {
    data class Success<T>(val result: T) : NetworkResult<T>()
    data class Error<T>(val error: String) : NetworkResult<T>()
    data class NetworkError<T>(val message: String) : NetworkResult<T>()
}