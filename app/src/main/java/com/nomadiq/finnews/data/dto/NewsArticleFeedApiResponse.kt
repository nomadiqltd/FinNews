package com.nomadiq.finnews.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * @author Michael Akakpo
 *
 * data class for saving root JSON response from the API.
 */

@JsonClass(generateAdapter = true)
data class NewsArticleFeedApiResponse(
    @Json(name = "response") val response: Response = Response()
)