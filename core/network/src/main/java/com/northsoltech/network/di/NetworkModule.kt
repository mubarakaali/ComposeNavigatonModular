package com.northsoltech.network.di
import com.northsoltech.network.services.ApiServices
import com.northsoltech.network.services.ApiServicesImp
import com.northsoltech.network.utils.ApiConstants.BASE_URL
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.koin.androidx.compose.get
import org.koin.dsl.module

val netWorkModule = module {
    single { providesHttpClient() }
    single { providesJson() }
    single<ApiServices>{ApiServicesImp(get()) }

}

fun providesHttpClient() = HttpClient(CIO){
    expectSuccess = true
    install(Logging){
        logger = Logger.DEFAULT
        level = LogLevel.ALL
        filter {request->
            request.url.host.contains("ktor.io")
        }
    }
    install(ContentNegotiation){
        json()
    }

    defaultRequest {
        url(BASE_URL){
            protocol = URLProtocol.HTTPS
            host = "ktor.io"
//            path("doc/")
//            parameters.apply {
//                append("token","abc123")
//                append("test_key","testValue")
//            }
//            header("custom_header","hello")
        }
    }
//    install(JsonFeature){
//
//        serializer = KotlinxSerializer()
//    }

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