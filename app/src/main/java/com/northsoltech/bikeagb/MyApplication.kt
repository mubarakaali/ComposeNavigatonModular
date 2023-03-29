package com.northsoltech.bikeagb

import android.app.Application
import com.northsoltech.domain.repositoryModule
import com.northsoltech.network.di.netWorkModule
import com.northsoltech.network.di.remoteDatasourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(netWorkModule,remoteDatasourceModule,repositoryModule,viewModelModule)
        }
    }
}