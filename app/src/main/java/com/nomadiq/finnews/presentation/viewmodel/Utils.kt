package com.nomadiq.finnews.presentation.viewmodel

import java.net.URLDecoder
import java.net.URLEncoder

/** URLs contain special characters which are not compatible with NavDeepLink arguments so URLs would require encoding and decoding
 *  The incoming Url is encoded as per the [sanitiseURL] function
 * */
fun sanitiseURL(url: String): String = URLEncoder.encode(url, "UTF-8")

/** URLs contain special characters which are not compatible with NavDeepLink arguments so URLs would require encoding and decoding
 *  The outgoing Url for the API call is further decoded as per the [validateURL] function */
fun validateURL(url: String): String = URLDecoder.decode(url, "UTF-8")