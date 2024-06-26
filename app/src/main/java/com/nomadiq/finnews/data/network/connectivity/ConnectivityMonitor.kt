package com.nomadiq.finnews.data.network.connectivity

/**
 *
 * @author - Michael Akakpo
 *
 * Check for connectivity manager status
 *
 * */
interface ConnectivityMonitor {
    fun isConnected(): Boolean
}