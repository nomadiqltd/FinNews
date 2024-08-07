package com.nomadiq.finnews.utils

import com.nomadiq.finnews.domain.model.NewsArticleFeedItem
import com.nomadiq.finnews.domain.model.NewsArticleItemDetail
import com.nomadiq.finnews.utils.TestConstants.API_URL
import com.nomadiq.finnews.utils.TestConstants.API_URL_TWO
import com.nomadiq.finnews.utils.TestConstants.BODY
import com.nomadiq.finnews.utils.TestConstants.IMG_URL
import com.nomadiq.finnews.utils.TestConstants.IMG_URL_TWO
import com.nomadiq.finnews.utils.TestConstants.SUBTITLE
import com.nomadiq.finnews.utils.TestConstants.SUBTITLE_NOT_FORMATTED
import com.nomadiq.finnews.utils.TestConstants.SUBTITLE_TWO
import com.nomadiq.finnews.utils.TestConstants.TITLE
import com.nomadiq.finnews.utils.TestConstants.TITLE_TWO

// Constants for tests
object TestConstants {
    const val UNKNOWN_ERROR = "Unknown Error occurred"
    const val NETWORK_ERROR_MESSAGE = "Connectivity issues, please check your connection"

    // Item 1
    const val TITLE = "Article 1"
    const val SUBTITLE = "16th Apr, 2024 - 4 min read"
    const val IMG_URL = "https://testimgurl.jpg"
    const val API_URL = "https://wwww.guardian.content/articleone"
    const val BODY =
        "loreum ipsum, loreum ipsum, loreum, ipsum,loreum, ipsum,loreum, ipsum,loreum, ipsum,loreum, ipsum, "

    // Item 2
    const val TITLE_TWO = "Article 2"
    const val SUBTITLE_TWO = "18th May, 2024 - 3 min read"
    const val IMG_URL_TWO = "https://testimgurl.jpg"
    const val API_URL_TWO = "https://wwww.guardian.content/articleone"

    // Item attributes
    const val SUBTITLE_NOT_FORMATTED = "2014-02-18T10:25:30Z"
}

// Item
val article = NewsArticleItemDetail(
    title = TITLE_TWO,
    date = SUBTITLE_TWO,
    body = BODY,
    imgUrl = IMG_URL_TWO,
)

// List of Articles
val listOfArticles = listOf(
    NewsArticleFeedItem(
        title = TITLE,
        subtitle = SUBTITLE,
        imgUrl = IMG_URL,
        apiUrl = API_URL
    ),
    NewsArticleFeedItem(
        title = TITLE_TWO,
        subtitle = SUBTITLE,
        imgUrl = IMG_URL_TWO,
        apiUrl = API_URL_TWO,
    )
)

// List of Articles (unformatted Subtitles)
val listOfTestCards = listOf(
    NewsArticleFeedItem(
        title = TITLE,
        subtitle = SUBTITLE_NOT_FORMATTED,
        imgUrl = IMG_URL,
        apiUrl = API_URL
    ),
    NewsArticleFeedItem(
        title = TITLE_TWO,
        subtitle = SUBTITLE_NOT_FORMATTED,
        imgUrl = IMG_URL_TWO,
        apiUrl = API_URL_TWO,
    )
)

// List of Articles (unformatted Subtitles)
val listOfSingleCard = listOf(
    NewsArticleFeedItem(
        title = TITLE,
        subtitle = SUBTITLE_NOT_FORMATTED,
        imgUrl = IMG_URL,
        apiUrl = API_URL
    ),
)