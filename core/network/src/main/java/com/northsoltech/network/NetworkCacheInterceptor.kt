package com.northsoltech.network

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class NetworkCacheInterceptor constructor(
    private val maxAge: Int,
    private val timeUnit: TimeUnit
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        return if(!response.cacheControl.noStore) {
            val cacheControl = CacheControl.Builder()
                .maxAge(maxAge, timeUnit)
                .build()

            response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }else{
            response
        }
    }
}