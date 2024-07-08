package com.nomadiq.finnews.presentation.utils

/**
 * @author - Michael Akakpo
 *
 * The Constants for Testing.
 *
 * Each relevant Composable has an associated [test Tag] which helps locate the item during testing
 *
 */

class ComposeTags {
    companion object {
        // Top AppBar
        const val TAG_NEWS_TOP_APP_BAR_VIEW = "NewsTopAppBar"
        const val TAG_SEARCH_ICON = "SearchIcon"
        const val TAG_NEWS_TOP_APP_BAR_VIEW_TITLE = "SearchIcon"

        // State loading
        const val TAG_CIRCULAR_PROGRESS_INDICATOR = "CircularProgressIndicator"
        const val TAG_ERROR_MESSAGE_DISPLAY_VIEW = "ErrorMessageDisplayView"

        // Article Card
        const val TAG_ARTICLE_CARD_IMAGE = "ArticleCardImage"
        const val TAG_ARTICLE_CARD_SUBTITLE = "ArticleCardSubtitle"
        const val TAG_ARTICLE_CARD_BOOKMARK = "ArticleCardBookmark"
        const val TAG_ARTICLE_CARD_SHARE = "ArticleCardShare"
    }
}
