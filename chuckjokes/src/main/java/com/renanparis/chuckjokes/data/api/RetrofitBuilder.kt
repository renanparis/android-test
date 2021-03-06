package com.renanparis.chuckjokes.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val URL = "https://api.chucknorris.io/jokes/"

    private fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build()
    }

    val jokeApiService: JokeApiService = getRetrofit().create(JokeApiService::class.java)
}