package com.nomadiq.chipdogstask.data.api

import android.content.Context
import com.nomadiq.chipdogstask.data.network.NetworkConnectivityInterceptor
import com.nomadiq.chipdogstask.data.network.connectivity.ConnectivityMonitor
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
    private val context: Context? = null,
    private val connectivityMonitor: ConnectivityMonitor,
    private val okHttpClientBuilder: OkHttpClient.Builder? = null,
    private val callFactory: Call.Factory? = null,
) {

    /**
     *  @logger - Debugging and logging request information
     *  @client - Call factory for Http calls through Retrofit
     *  @Moshi - The binding between JSON Responses and their respective object counterparts
     *  @Retrofit - Make Http Calls to Dog Ceo Api
     *
     * */

    // TODO - Could hide the URL and any API Keys inside a buildConfigField

    private val BASE_URL = "https://dog.ceo/api/"

    private val logger =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .addInterceptor(NetworkConnectivityInterceptor(connectivityMonitor))
        .build()

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // OkHttp Builder
    private val clientBuilder: OkHttpClient.Builder by lazy {
        okHttpClientBuilder ?: defaultClientBuilder
    }

    // Back up Client Builder for testing
    private val defaultClientBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient()
            .newBuilder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(NetworkConnectivityInterceptor(connectivityMonitor))
    }

    // Retrofit Builder
    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
    }

    fun <S> createService(serviceClass: Class<S>): S {
        val usedCallFactory = this.callFactory ?: clientBuilder.build()
        return retrofitBuilder.callFactory(usedCallFactory).build().create(serviceClass)
    }
}