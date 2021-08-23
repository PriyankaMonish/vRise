package com.vrise.bazaar.newpages.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DownloadClient {

    private var retrofit: Retrofit? = null

    val baseUrl = "http://ariesestrrado.com/paytoall/uploads/"
    private val okHttpClient = OkHttpClient.Builder()
            .readTimeout(10000, TimeUnit.SECONDS)
            .connectTimeout(10000, TimeUnit.SECONDS)
            .build()

    fun getClient(): Retrofit {
        if (retrofit == null) {
            return getRetrofitClient()
        }
        return retrofit!!
    }


    private fun getRetrofitClient(): Retrofit {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit!!
    }

}

