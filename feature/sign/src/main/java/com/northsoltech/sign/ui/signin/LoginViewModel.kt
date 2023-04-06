package com.northsoltech.sign.ui.signin

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.northsoltech.domain.models.ApiResource
import com.northsoltech.domain.repositories.signing.SigningRepository
import com.northsoltech.framework.states.UiState
import com.northsoltech.sign.ui.events.SignInTopEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel  constructor(
    private val signingRepository: SigningRepository,
) : ViewModel() {

    val uiState = mutableStateOf<UiState>(UiState.Idle)
    private var _topLevelEvents = MutableSharedFlow<SignInTopEvents>()
    val topLevelEvents:SharedFlow<SignInTopEvents> = _topLevelEvents


    fun onEventPerform(topEvents: SignInTopEvents){
        viewModelScope.launch {
        _topLevelEvents.emit(topEvents)
        }
    }

    fun userLogin(
        phoneNo: String,
        password: String,
    ) {
        viewModelScope.launch {
            Log.d("jejeje", "userLogin: phoneNo $phoneNo,  password $password")
            if (phoneNo.isEmpty() || password.isEmpty()) {
                _topLevelEvents.emit(SignInTopEvents.UserAuthenticationFailed("Please fill the data correctly"))
                return@launch
            }
            signingRepository.signing(
                phoneNo = phoneNo,
                password = password
            ).collect {
                Log.d("jejeje", "viewModel userLogin collect ${it}")
                when (it) {
                    is ApiResource.Loading -> {
                        uiState.value = UiState.Loading
                    }
                    is ApiResource.Valid -> {
                        uiState.value = UiState.Success
                        Log.d("jejeje", "viewModel userLogin Sucess ${it.data}")
                        if (it.data.token == null) {
                            _topLevelEvents.emit(SignInTopEvents.UserAuthenticationFailed("Something went wrong"))
                        } else {
                            _topLevelEvents.emit(SignInTopEvents.UserAuthenticated)
//                            userDataPreferenceManager.updateData { userPreference ->
//                                userPreference.copy(
//                                    token = it.data.token!!.toPreferenceToken(),
//                                    user = it.data.user!!.toPreferenceUser()
//                                )
//                            }
//                            onUserAuthentcated()
                        }
                    }
                    is ApiResource.Invalid -> {
                        uiState.value = UiState.Error(it.message)
                        _topLevelEvents.emit(SignInTopEvents.UserAuthenticationFailed(it.message))
                    }
                }
            }
        }

    }

//    private fun Token.toPreferenceToken(): TokenPreference =
//        TokenPreference(accessToken = accessToken!!)
//
//    private fun User.toPreferenceUser(): UserPreference = UserPreference(
//        id = id!!,
//        name = name!!,
//        phoneNo = phoneNo!!,
//        userType = userType!!
//    )

}