package com.nomadiq.finnews.data.network.connectivity

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException

/**
 *
 * @author - Michael Akakpo
 *
 * Interceptor used to log and check for connectivity issues
 *
 * */
class NetworkConnectivityInterceptor(
    private val connectivityMonitor: ConnectivityMonitor
) : Interceptor {

    companion object {
        const val INTERCEPTOR_ERROR_MESSAGE = "No internet connection"
        const val NETWORK_ERROR_HINT = "Poor internet connectivity, check your connection"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectivityMonitor.isConnected()) {
            try {
                chain.proceed(chain.request())
            } catch (e: IOException) {
                createErrorResponse(chain.request(), INTERCEPTOR_ERROR_MESSAGE)
            }
        } else {
            // Return a custom error response or throw an exception
            createErrorResponse(chain.request(), NETWORK_ERROR_HINT)
        }
    }

    private fun createErrorResponse(request: Request, message: String): Response {
        val errorResponseBody =
            message.toResponseBody("text/plain; charset=utf-8".toMediaTypeOrNull())
        return Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(503) // Service Unavailable
            .message(message)
            .body(errorResponseBody)
            .build()
    }
}