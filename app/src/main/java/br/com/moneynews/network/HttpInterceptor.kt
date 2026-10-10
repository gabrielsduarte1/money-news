package br.com.moneynews.network

import br.com.moneynews.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HttpInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("x-api-key", BuildConfig.API_KEY)
            .build()
        val response = chain.proceed(request)
        return response
    }
}