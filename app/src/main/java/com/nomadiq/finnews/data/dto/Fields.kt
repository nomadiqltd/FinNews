package com.nomadiq.finnews.data.dto

import com.squareup.moshi.JsonClass

/**
 * @author Michael Akakpo
 *
 * data class for saving JSON response from the API.
 */
@JsonClass(generateAdapter = true)
data class Fields(
    val headline: String = "",
    val thumbnail: String = ""
)