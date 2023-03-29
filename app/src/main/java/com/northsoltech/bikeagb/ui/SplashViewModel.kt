package com.northsoltech.bikeagb.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {
    var isTimeOut = mutableStateOf(false)

    //    val isTimeOut = _isTimeOut.asStateFlow()
    init {
        if (!isTimeOut.value) goToNextScreen()
    }

    private fun goToNextScreen() {
        viewModelScope.launch {
            delay(2000)
            isTimeOut.value = true
        }
    }
}