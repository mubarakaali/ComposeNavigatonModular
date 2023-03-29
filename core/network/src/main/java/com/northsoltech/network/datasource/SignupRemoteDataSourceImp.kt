package com.northsoltech.network.datasource

import android.util.Log
import com.northsoltech.domain.models.ApiResource
import com.northsoltech.domain.models.signup.SignupRequest
import com.northsoltech.domain.models.signup.SignupResponse
import com.northsoltech.domain.models.transform
import com.northsoltech.domain.remote.SignupRemoteDataSource
import com.northsoltech.network.ApiServices
import com.northsoltech.network.models.signup.SignupRequestDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SignupRemoteDataSourceImp constructor(
    private val apiServices: ApiServices
): BaseRemoteDataSource(),SignupRemoteDataSource {

    override  fun signup(signupRequest: SignupRequest): Flow<ApiResource<SignupResponse>> = safeApiCall {
        Log.d("jejeje ", "signupRequest: SignupDataSourceImp.... $signupRequest ")
        apiServices.signup(SignupRequestDTO.fromDomain(signupRequest))
    }.map { it->
        it.transform {
            Log.d("jejeje ", "signupRespone: SignupDataSourceImp.... $it ")
            it.toDomain()
        }
    }
}