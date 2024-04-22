package com.nomadiq.finnews.data.articledetail

data class Content(
    val apiUrl: String = "",
    val fields: Fields = Fields(),
    val id: String = "",
    val isHosted: Boolean = false,
    val sectionId: String = "",
    val sectionName: String = "",
    val type: String = "",
    val webPublicationDate: String = "",
    val webTitle: String = "",
    val webUrl: String = ""
)