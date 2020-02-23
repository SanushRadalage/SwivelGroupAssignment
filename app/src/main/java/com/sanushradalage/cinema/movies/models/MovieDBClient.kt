package com.sanushradalage.cinema.movies.models

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_KEY = "c2fb1a22"
const val BASE_URL = "https://www.omdbapi.com/"

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

object MovieDBClient {

    fun getClient(): MovieDBInterface {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        //interceptor is not working -> need to Test

//        val requestInterceptor = Interceptor { chain ->
//
//            val url = chain.request()
//                .url()
//                .newBuilder()
//                .addQueryParameter("apikey", API_KEY)
//                .build()
//
//            val request = chain.request()
//                .newBuilder()
//                .url(url)
//                .build()
//
//            return@Interceptor chain.proceed(request)
//        }
//
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(requestInterceptor)
//            .connectTimeout(60, TimeUnit.SECONDS)
//            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDBInterface::class.java)
    }
}