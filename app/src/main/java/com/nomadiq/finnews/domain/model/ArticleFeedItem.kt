package com.nomadiq.finnews.domain.model

import com.squareup.moshi.Json

/**
 * @author Michael Akakpo
 *
 * Dog Breed Entity class for [ArticleFeedItem], to be used in domain and presentation layers
 *
 */
data class ArticleFeedItem(
   val title : String = "",
   val subtitle : String = "",
   val imgUrl : String = " ",
)
