package com.nomadiq.finnews.data.dto

import com.squareup.moshi.JsonClass

/**
 * @author Michael Akakpo
 *
 * data class for list of result items (Article).
 */
@JsonClass(generateAdapter = true)
data class Result(
    val apiUrl: String = "",
    val fields: Fields = Fields(),
    val id: String = "",
    val isHosted: Boolean = false,
    val pillarId: String? = "",
    val pillarName: String? = "",
    val sectionId: String = "",
    val sectionName: String = "",
    val type: String = "",
    val webPublicationDate: String = "",
    val webTitle: String = "",
    val webUrl: String = ""
)