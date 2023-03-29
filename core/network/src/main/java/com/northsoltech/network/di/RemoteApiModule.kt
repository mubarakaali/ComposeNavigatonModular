package com.northsoltech.network.di

import com.northsoltech.domain.remote.SigningDataSource
import com.northsoltech.domain.remote.SignupRemoteDataSource
import com.northsoltech.network.datasource.SigningDataSourceImp
import com.northsoltech.network.datasource.SignupRemoteDataSourceImp
import org.koin.dsl.module

val remoteDatasourceModule = module {
    single <SignupRemoteDataSource>{ SignupRemoteDataSourceImp(get()) }
    single <SigningDataSource>{ SigningDataSourceImp(get()) }
}
