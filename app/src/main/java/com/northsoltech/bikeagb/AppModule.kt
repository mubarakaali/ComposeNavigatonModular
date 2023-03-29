package com.northsoltech.bikeagb

import com.northsoltech.bikeagb.ui.SplashViewModel
import com.northsoltech.sign.ui.signin.LoginViewModel
import com.northsoltech.sign.ui.signup.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
   viewModel{ SplashViewModel()}
   viewModel{ LoginViewModel(get())}
   viewModel{ SignupViewModel(get())}
}