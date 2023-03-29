package com.northsoltech.network.datasource

import android.util.Log
import com.northsoltech.domain.models.ApiResource
import com.northsoltech.domain.models.loging.LoginData
import com.northsoltech.domain.models.transform
import com.northsoltech.domain.remote.SigningDataSource
import com.northsoltech.network.ApiServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SigningDataSourceImp constructor(
     val apiServices: ApiServices

): BaseRemoteDataSource(), SigningDataSource {

    override fun signing(phoneNo: String, password: String): Flow<ApiResource<LoginData>> = safeApiCall {
        Log.d("jejeje", "signing: SigningDataSourceImp phoneNo.... $phoneNo  password $password")
        apiServices.signing(phoneNo, password)
    }.map { it->
        it.transform {
            it.toDomain()
        }
    }
}