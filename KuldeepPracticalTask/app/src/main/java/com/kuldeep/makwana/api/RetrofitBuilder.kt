package com.kuldeep.makwana.api

import com.google.gson.GsonBuilder
import com.kuldeep.makwana.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Author by Kuldeep Makwana, Email kuldeepmakwana3977@gmail.com, Date on 22-06-2021.
 * PS: Not easy to write code, please indicate.
 */
object RetrofitBuilder {

    private const val BASE_URL = BuildConfig.hostAPI

    private var retrofit:Retrofit?=null
    fun getApiClient(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .build()
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit!!
    }
}
