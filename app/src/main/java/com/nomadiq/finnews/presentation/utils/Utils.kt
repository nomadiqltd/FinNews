package com.nomadiq.finnews.presentation.utils

import android.os.Build
import java.net.URLDecoder
import java.net.URLEncoder
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.random.Random

/** URLs contain special characters which are not compatible with NavDeepLink arguments
 * so URLs would require encoding and decoding
 *
 * The incoming Url is encoded as per the [sanitiseURL] function
 * */
fun sanitiseURL(url: String): String = URLEncoder.encode(url, "UTF-8")

/** URLs contain special characters which are not compatible with NavDeepLink arguments
 *  so URLs would require encoding and decoding
 *  The outgoing Url for the API call is further decoded as per the [validateURL] function */
fun validateURL(url: String): String = URLDecoder.decode(url, "UTF-8")

/** display the incoming [OffsetDateTime] from the API and convert it to human readable date stamp */
fun displaySimpleTimeStamp(currentDate: String): String {

    val randomMinRead = Random.nextInt(3, 8)
    // Apr 01, 2024
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val offsetDateTime = OffsetDateTime.parse(currentDate)
        // Define the desired date format
        val formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy", Locale.ENGLISH)
        // Format the OffsetDateTime to a simple date string
        val simpleDate = offsetDateTime.format(formatter)
        // Just for visual to the subtitle
        return simpleDate.plus(" - $randomMinRead min read")
    } else {
        return currentDate
    }
}