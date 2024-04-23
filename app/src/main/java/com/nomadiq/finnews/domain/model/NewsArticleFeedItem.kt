package com.nomadiq.finnews.domain.model

import com.squareup.moshi.Json


/**
 * @author Michael Akakpo
 *
 * Dog Breed Entity class for [NewsArticleFeedItem], to be used in domain and presentation layers
 *
 */

data class NewsArticleFeedItem(
    @Json(name = "headline")
    val title: String = "",
    @Json(name = "webPublicationDate")
    val subtitle: String = "",
    @Json(name = "thumbnail")
    val imgUrl: String = "",
    @Json(name = "apiUrl")
    val apiUrl: String = "",
)
