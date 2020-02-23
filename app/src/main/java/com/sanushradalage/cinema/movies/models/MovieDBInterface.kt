package com.sanushradalage.cinema.movies.models

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBInterface {

    @GET("?apikey=c2fb1a22&s=Batman") //use API key in here because interceptor function in MovieDBClient in not working properly
    fun getMovieDetails(@Query("page") page: Int): Single<Movie>

}