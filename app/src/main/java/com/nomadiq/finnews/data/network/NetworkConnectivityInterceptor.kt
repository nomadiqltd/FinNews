package com.nomadiq.finnews.data.network

import com.nomadiq.finnews.data.network.connectivity.ConnectivityMonitor
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

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
    override fun intercept(chain: Interceptor.Chain): Response {
        if (connectivityMonitor.isConnected()) {
            return chain.proceed(chain.request())
        } else {
            Timber.tag("NetworkConnectivityInterceptor ")
                .d(" ::  ==> Poor internet connectivity, check your connection ")
        }
        TODO("Provide the return value")
    }
}