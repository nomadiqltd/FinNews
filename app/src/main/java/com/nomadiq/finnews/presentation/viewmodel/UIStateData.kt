package com.nomadiq.finnews.presentation.viewmodel

/**
 * @author Michael Akakpo
 *
 * A generic interface that represents the screen contract for various states that a screen can be in (typically from the viewmodel)

 * [Data] - Success 200 - We have data to display
 * [IsLoading] - Loading state
 * [Empty] - No Data returned - Empty state
 * [Error] - General error state
 * [NetworkError] - Offline screen state
 */

/** A sealed class representing preset [UIState] types to facilitate error management **/
interface UiState<T> {
    data class Data<T>(val data: T) : UiState<T>
    data class Error<T>(val error: String) : UiState<T>
    data class NetworkError<T>(val message: String?) : UiState<T>
}