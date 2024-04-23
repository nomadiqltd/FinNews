package com.nomadiq.finnews.data.model.articledetail

data class Response(
    val content: Content = Content(),
    val status: String = "",
    val total: Int = 0,
    val userTier: String = ""
)