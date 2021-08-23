package com.vrise.bazaar.ex.retrofit

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vrise.bazaar.ex.app.BaseApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Retrofit Client
 */
class RetroClient private constructor(contexts: Context){

    private var payEnvironment: PayEnvironment? = null
    var imageUrl = ""
    private var baseUrl = ""
    private var retrofit: Retrofit? = null
    private var gSon: Gson
    private var okHttpClient: OkHttpClient


    init {
        payEnvironment = (contexts.applicationContext as BaseApp).getAppEnvironment()
        payEnvironment?.let {
            imageUrl = it.imageUrl()
            baseUrl = it.basUrl()
        }

        gSon = GsonBuilder().setLenient().create()
        okHttpClient = OkHttpClient.Builder()
            .readTimeout(10000, TimeUnit.SECONDS)
            .connectTimeout(10000, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }


    fun getClient(): Retrofit {
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gSon))
                .build()
            return retrofit!!
        }
        return retrofit!!
    }
    companion object : InstanceObject<RetroClient, Context>(::RetroClient)


}



