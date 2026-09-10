package br.com.moneynews.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://economia.awesomeapi.com.br/"

    val awesomeApiService: AwesomeApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AwesomeApiService::class.java)
}