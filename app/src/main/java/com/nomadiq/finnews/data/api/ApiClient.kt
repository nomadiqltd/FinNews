package com.nomadiq.finnews.data.api

import com.nomadiq.finnews.BuildConfig.*
import com.nomadiq.finnews.data.network.NetworkConnectivityInterceptor
import com.nomadiq.finnews.data.network.connectivity.ConnectivityMonitor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * @author - Michael Akakpo
 *
 * @ApiClient - The object assembling the retrofit builder, initiating the connect and interfacing with the API endpoint
 *
 */

class ApiClient(
    private val connectivityMonitor: ConnectivityMonitor,
    private val okHttpClientBuilder: OkHttpClient.Builder? = null,
    private val callFactory: Call.Factory? = null,
) {

    /**
     *  @logger - Debugging and logging request information
     *  @client - Call factory for Http calls through Retrofit
     *  @Moshi - Maps the incoming JSON Responses and to their respective response data objects
     *  @Retrofit - Make Http Calls to Guardian News Api
     *
     * */

    private val logger =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val client =
        OkHttpClient.Builder()
            .apply { addInterceptor(NetworkConnectivityInterceptor(connectivityMonitor)) }.build()

    private val moshi: Moshi = Moshi.Builder().apply { add(KotlinJsonAdapterFactory()) }.build()

    // OkHttp Builder
    private val clientBuilder: OkHttpClient.Builder by lazy {
        okHttpClientBuilder ?: defaultClientBuilder
    }

    // Back up Client Builder for testing
    private val defaultClientBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient().newBuilder().apply {
            addInterceptor(logger)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            addInterceptor(NetworkConnectivityInterceptor(connectivityMonitor))
        }
    }

    // Retrofit Builder
    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(MoshiConverterFactory.create(moshi))
            client(client)
        }
    }

    fun <Service> createServiceFor(service: Class<Service>): Service {
        val callFactory = this.callFactory ?: clientBuilder.build()
        return retrofitBuilder.callFactory(callFactory).build().create(service)
    }
}