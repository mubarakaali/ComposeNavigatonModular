package com.northsoltech.domain.models.signup

import com.northsoltech.bikeagb.ui.SplashViewModel
import com.northsoltech.sign.ui.signin.LoginViewModel
import com.northsoltech.sign.ui.signup.SignupViewModel
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class AppModuleTest:KoinTest {
    private val splashViewModel: SplashViewModel by inject()
    private val signupViewModel:SignupViewModel by inject()
    private val loginViewModel:LoginViewModel by inject()


    @Test
    fun `should inject by app component viewmodels`(){
        startKoin {
            module {
                viewModel { splashViewModel }
                viewModel { signupViewModel }
                viewModel { loginViewModel }
            }
        }
    }
}