package com.nomadiq.finnews.data.network.connectivity

class FakeConnectivityMonitor : ConnectivityMonitor {
    override fun isConnected(): Boolean {
        return true
    }
}