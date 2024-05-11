package com.nomadiq.finnews.data.network.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 *
 * @author - Michael Akakpo
 *
 * Check connectivity manager for internet connectivity
 *
 * */
class ConnectivityMonitorImpl(private val context: Context) : ConnectivityMonitor {
    override fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val activeNetwork = connectivityManager?.activeNetwork
        return connectivityManager?.getNetworkCapabilities(activeNetwork)?.let {
            it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                    it.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)
        } ?: false
    }
}