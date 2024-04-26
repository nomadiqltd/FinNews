package com.nomadiq.finnews.presentation.viewmodel

/**
 *
 * Basic Error types from API call to determine which state for
 * state Composables to display if error occurs while fetching data
 *
 * */
enum class ErrorType {
    NETWORK_ERROR, GENERAL_ERROR
}