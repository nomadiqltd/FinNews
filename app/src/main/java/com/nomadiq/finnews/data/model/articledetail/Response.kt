package com.nomadiq.finnews.data.model.articledetail

/**
 * @author Michael Akakpo
 *
 * data class for items detail.
 */
data class Response(
    val content: Content = Content(),
    val status: String = "",
    val total: Int = 0,
    val userTier: String = ""
)