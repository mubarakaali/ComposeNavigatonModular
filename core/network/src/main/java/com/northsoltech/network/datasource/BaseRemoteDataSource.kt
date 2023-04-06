package com.northsoltech.network.datasource

import com.northsoltech.domain.models.ApiResource
import com.northsoltech.network.utils.getNetworkErrorMessage
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseRemoteDataSource {
      inline fun <reified T:Any>safeApiCall(
          crossinline call:suspend ()-> HttpResponse
    ): Flow<ApiResource<T>> =
        flow {
            emit(ApiResource.Loading())
            try {
                emit(safeApiResult(call.invoke()))
            } catch (e: java.lang.Exception) {
                emit(ApiResource.Invalid(message = getNetworkErrorMessage(e)))
            }
        }
     suspend inline fun <reified T:Any>safeApiResult(httpResponse: HttpResponse): ApiResource<T> {
        return if (httpResponse.status.isSuccess()){
            ApiResource.Valid(httpResponse.body())
        }else{
            ApiResource.Invalid(message = httpResponse.status.description)
        }
    }
}