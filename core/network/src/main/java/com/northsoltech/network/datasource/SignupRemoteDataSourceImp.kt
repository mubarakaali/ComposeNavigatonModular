package com.northsoltech.network.datasource

import android.util.Log
import com.northsoltech.domain.models.ApiResource
import com.northsoltech.domain.models.signup.SignupRequest
import com.northsoltech.domain.models.signup.SignupResponse
import com.northsoltech.domain.models.transform
import com.northsoltech.domain.remote.SignupRemoteDataSource
import com.northsoltech.network.services.ApiServices
import com.northsoltech.network.models.signup.SignupRequestDTO
import com.northsoltech.network.models.signup.SignupResponseDTO
import com.northsoltech.network.utils.getErrorMessage
import com.northsoltech.network.utils.getNetworkErrorMessage
import io.ktor.client.call.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SignupRemoteDataSourceImp constructor(
    private val apiServices: ApiServices
):SignupRemoteDataSource {

    override  fun signup(signupRequest: SignupRequest): Flow<ApiResource<SignupResponse>> {
        return flow {
            try {
                ApiResource.Loading<SignupResponse>()
                val response = apiServices.signup(SignupRequestDTO.fromDomain(signupRequest)).body<SignupResponseDTO>()
                if (response.statusCode!!.equals("00")){
                    ApiResource.Valid(response.toDomain())
                }else{
                    ApiResource.Invalid(message = getErrorMessage(response.statusCode!!))
                }
            }catch (e:java.lang.Exception){
                ApiResource.Invalid(message = getNetworkErrorMessage(e))
            }
        }
    }
}