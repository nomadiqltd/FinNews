package com.nomadiq.finnews.domain.model

import com.squareup.moshi.Json

/**
 * @author Michael Akakpo
 *
 * Entity class for [NewsChannelItem], to be used in domain and presentation layers
 *
 */
data class NewsChannelItem(
    val name: String,
    val isVerified: Boolean = false,
)
