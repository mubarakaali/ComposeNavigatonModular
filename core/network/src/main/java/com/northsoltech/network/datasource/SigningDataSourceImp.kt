package com.northsoltech.network.datasource

import com.northsoltech.domain.models.ApiResource
import com.northsoltech.domain.models.loging.LoginData
import com.northsoltech.domain.models.transform
import com.northsoltech.domain.remote.SigningDataSource
import com.northsoltech.network.models.signing.LoginResponse
import com.northsoltech.network.services.ApiServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SigningDataSourceImp constructor(
    private val apiServices: ApiServices
):SigningDataSource,BaseRemoteDataSource() {

    override fun signing(phoneNo: String, password: String): Flow<ApiResource<LoginData>> = safeApiCall<LoginResponse> {
        apiServices.signing(phoneNo,password)
    }.map {
        it.transform {loginRes->
            loginRes.toDomain()
        }
    }
}