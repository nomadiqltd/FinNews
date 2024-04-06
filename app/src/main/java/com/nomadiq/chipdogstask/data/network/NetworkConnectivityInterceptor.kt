package com.nomadiq.chipdogstask.data.network

import android.util.Log
import com.nomadiq.chipdogstask.data.network.connectivity.ConnectivityMonitor
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectivityInterceptor(
    private val connectivityMonitor: ConnectivityMonitor
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (connectivityMonitor.isConnected()) {
            return chain.proceed(chain.request())
        } else {
            Log.d("NetworkConnectivityInterceptor ", " ::  ==> Poor internet connectivity, check your connection ")
            // throw NetworkConnectivityException()
            TODO()
        }
    }
}