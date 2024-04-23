package com.nomadiq.finnews.data.model.article

import com.squareup.moshi.JsonClass

/**
 * @author Michael Akakpo
 *
 * data class for saving root JSON response from the API.
 */
@JsonClass(generateAdapter = true)
data class Response(
    val currentPage: Int = 0,
    val orderBy: String = "",
    val pageSize: Int = 0,
    val pages: Int = 0,
    val results: List<Result> = listOf(),
    val startIndex: Int = 0,
    val status: String = "",
    val total: Int = 0,
    val userTier: String = ""
)