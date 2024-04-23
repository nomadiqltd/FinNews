package com.nomadiq.finnews.presentation.viewmodel

import java.net.URLDecoder
import java.net.URLEncoder

/** URLs contain special characters which are not compatible with NavDeepLink arguments so URLs would require encoding and decoding
 *  The incoming Url is encoded as per the [encodeUrl] function
 * */
fun encodeURL(url: String): String = URLEncoder.encode(url, "UTF-8")

/** URLs contain special characters which are not compatible with NavDeepLink arguments so URLs would require encoding and decoding
 *  The incoming Url is encoded as per the [decodeURL] function */
fun decodeURL(url: String): String = URLDecoder.decode(url, "UTF-8")