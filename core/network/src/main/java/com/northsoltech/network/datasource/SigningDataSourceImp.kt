package com.northsoltech.network.datasource

import com.northsoltech.domain.models.ApiResource
import com.northsoltech.domain.models.loging.LoginData
import com.northsoltech.domain.remote.SigningDataSource
import com.northsoltech.network.models.signing.LoginResponse
import com.northsoltech.network.services.ApiServices
import com.northsoltech.network.utils.getErrorMessage
import com.northsoltech.network.utils.getNetworkErrorMessage
import io.ktor.client.call.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SigningDataSourceImp constructor(
    private val apiServices: ApiServices
):SigningDataSource {

    override fun signing(phoneNo: String, password: String): Flow<ApiResource<LoginData>>{
        return flow {
            try {
                emit(ApiResource.Loading())
                val loginResponse = apiServices.signing(phoneNo,password).body<LoginResponse>()
                if (loginResponse.statusCode!!.equals("00")){
                emit(ApiResource.Valid(loginResponse.toDomain()))
                }else{
                    emit(ApiResource.Invalid<String>(message = getErrorMessage(loginResponse.statusCode!!)))
                }
            }catch (e:java.lang.Exception){
                emit(ApiResource.Invalid<String>(message = getNetworkErrorMessage(e)))
            }

        }

    }
}