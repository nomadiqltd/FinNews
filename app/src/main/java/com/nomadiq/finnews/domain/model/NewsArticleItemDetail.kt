package com.nomadiq.finnews.domain.model

import com.squareup.moshi.Json

/**
 * @author Michael Akakpo
 *
 * News Article Entity class to represent article details
 *
 */
data class NewsArticleItemDetail(
    @Json(name = "thumbnail")
    val imgUrl: String = "",
    @Json(name = "headline")
    val title: String = "",
    @Json(name = "webPublicationDate")
    val date: String = "",
    @Json(name = "body")
    val body: String = "",
)
