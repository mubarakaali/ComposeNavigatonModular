package com.northsoltech.network.services

import android.util.Log
import com.northsoltech.network.models.signup.SignupRequestDTO
import com.northsoltech.network.utils.ApiConstants.SIGNIN
import com.northsoltech.network.utils.ApiConstants.SIGNUP
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class ApiServicesImp constructor(
    private val client: HttpClient
):ApiServices {
    override suspend fun signup(signupRequestDTO: SignupRequestDTO): HttpResponse {
      return  client.post {
           url(SIGNUP)
           setBody(signupRequestDTO)
       }
    }

    override suspend fun signing(phoneNo: String, password: String): HttpResponse {
        return client.post {
            url(SIGNIN)
            parameter("phone_no",phoneNo)
            parameter("password",password)
        }
    }
}