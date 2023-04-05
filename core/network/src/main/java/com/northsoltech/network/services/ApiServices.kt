package com.northsoltech.network.services

import com.northsoltech.network.models.signing.LoginResponse
import com.northsoltech.network.models.signup.SignupRequestDTO
import com.northsoltech.network.models.signup.SignupResponseDTO
import io.ktor.client.statement.*

interface ApiServices {
   suspend fun signup(signupRequestDTO: SignupRequestDTO):HttpResponse//SignupResponseDTO
    suspend fun signing(phoneNo:String,password:String):HttpResponse//LoginResponse
//    phone_no
//    password
}