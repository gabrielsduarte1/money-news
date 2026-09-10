package br.com.moneynews.network

import br.com.moneynews.model.QuoteResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AwesomeApiService {
    @GET("json/last/{moedas}")
    suspend fun getQuotes(@Path("moedas") moedas: String): Map<String, QuoteResponse>
}