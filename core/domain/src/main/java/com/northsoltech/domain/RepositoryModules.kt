package com.northsoltech.domain

import com.northsoltech.domain.repositories.signing.SigningRepository
import com.northsoltech.domain.repositories.signing.SigningRepositoryImp
import com.northsoltech.domain.repositories.signup.SignupRepository
import com.northsoltech.domain.repositories.signup.SignupRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    single<SignupRepository> { SignupRepositoryImp(get()) }
    single<SigningRepository> { SigningRepositoryImp(get()) }
}
