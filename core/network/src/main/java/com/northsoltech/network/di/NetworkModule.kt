package com.northsoltech.network.di

import com.core.network.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.northsoltech.network.ApiServices
import com.northsoltech.network.NetworkCacheInterceptor
import com.northsoltech.network.utils.ApiConstants.BASE_URL
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val netWorkModule = module {
    single { providesJson() }
    single { provideLoggingInterceptor() }
    single { provideNetworkCacheInterceptor() }
    single { provideOkHttp(get(), get()) }
    single { providesApiServices(get()) }
    single { providesRetrofit(get(), get()) }

}

fun providesJson(): Json {
    val module = SerializersModule {

    }
    return Json {
        isLenient = true
        ignoreUnknownKeys = true
        serializersModule = module
    }
}


fun provideLoggingInterceptor() =
    HttpLoggingInterceptor().setLevel(
        if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    )

fun provideNetworkCacheInterceptor() = NetworkCacheInterceptor(7, TimeUnit.DAYS)
fun provideOkHttp(
    interceptor: HttpLoggingInterceptor,
    networkCacheInterceptor: NetworkCacheInterceptor,
) = OkHttpClient.Builder()
    .connectTimeout(10L, TimeUnit.MILLISECONDS)
    .writeTimeout(10L, TimeUnit.MILLISECONDS)
    .readTimeout(10L, TimeUnit.MILLISECONDS)
    .addNetworkInterceptor(networkCacheInterceptor)
    .addInterceptor(interceptor)
    .build()


fun providesApiServices(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)
fun providesRetrofit(
    json: Json,
    okHttpClient: OkHttpClient
): Retrofit {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
}