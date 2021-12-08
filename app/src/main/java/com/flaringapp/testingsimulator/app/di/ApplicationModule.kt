package com.flaringapp.testingsimulator.app.di

import com.flaringapp.testingsimulator.BuildConfig
import com.flaringapp.testingsimulator.app.Constants
import com.flaringapp.testingsimulator.core.data.network.adapter.RetrofitAdapterConfig
import com.flaringapp.testingsimulator.core.data.network.common.useragent.UserAgentNameProviderImpl
import com.flaringapp.testingsimulator.core.data.network.common.useragent.UserAgentProvider
import org.koin.dsl.module

val ApplicationModule = module {

    single<UserAgentProvider> {
        UserAgentNameProviderImpl(
            context = get(),
            versionCode = BuildConfig.VERSION_CODE.toString(),
            versionName = BuildConfig.VERSION_NAME,
        )
    }

    single {
        RetrofitAdapterConfig(
            connectTimeout = Constants.API_CALL_CONNECT_TIMEOUT,
            readTimeout = Constants.API_CALL_READ_TIMEOUT,
            writeTimeout = Constants.API_CALL_WRITE_TIMEOUT,
        )
    }

}