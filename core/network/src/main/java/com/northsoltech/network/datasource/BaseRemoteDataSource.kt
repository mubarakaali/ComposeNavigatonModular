//package com.northsoltech.network.datasource
//
//import android.util.Log
//import com.northsoltech.domain.models.ApiResource
//import com.northsoltech.network.utils.NetworkUtils.getNetworkErrorMessage
//import io.ktor.client.call.*
//import io.ktor.client.statement.*
//import io.ktor.http.*
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import retrofit2.Response
//
//abstract class BaseRemoteDataSource {
//
//    /**
//     * This method  will safely invoke the remote api call and return a flow of [Resource]s
//     */
//      fun <T : Any> safeApiCall( // TODO: Move this to a retrofit adapter
//        call: suspend () -> Response<T>
//    ): Flow<ApiResource<T>> =
//        flow {
//            emit(ApiResource.Loading())
//            try {
//                emit(safeApiResult(call.invoke()))
//            } catch (e: Exception) {
//                e.printStackTrace()
//                emit(ApiResource.Invalid<T>(message = getNetworkErrorMessage(e)))
//            }
//        }
//
//
//    private  fun <T> safeApiResult(response: Response<T>): ApiResource<T>{
//        Log.d("jejeje", "BaseRemoteDataSourde safeApiResult: statusCode ${response.code()}")
//        return when {
//            response.isSuccessful -> {
//                ApiResource.Valid(response.body()!!)
//            }
//            else -> ApiResource.Invalid(message = response.message())
//        }
//        return null
//    }
//
//
//}