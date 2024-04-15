package com.nomadiq.finnews.domain.model

/**
 * @author Michael Akakpo
 *
 * Entity class for [NewsChannelFeedItem], to be used in domain and presentation layers
 *
 */
data class NewsChannelFeedItem(
    val name: String,
    val isVerified: Boolean = false,
)
