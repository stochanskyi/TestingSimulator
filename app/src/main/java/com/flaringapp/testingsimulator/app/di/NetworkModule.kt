package com.flaringapp.testingsimulator.app.di

import com.flaringapp.testingsimulator.BuildConfig
import com.flaringapp.testingsimulator.app.Constants
import com.flaringapp.testingsimulator.core.data.network.adapter.NetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.RetrofitAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.RetrofitAdapterConfig
import com.flaringapp.testingsimulator.core.data.network.common.modifier.RequestModifierAnnotationProcessor
import com.flaringapp.testingsimulator.core.data.network.common.useragent.UserAgentNameProviderImpl
import com.flaringapp.testingsimulator.core.data.network.common.useragent.UserAgentProvider
import com.flaringapp.testingsimulator.data.network.modifiers.CommonRequestModifierAnnotationProcessor
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

val NetworkModule = module {

    single<NetworkAdapter> {
        RetrofitAdapter(
            userAgentProvider = get(),
            modifierAnnotationProcessor = getOrNull(),
            config = RetrofitAdapterConfig(
                connectTimeout = Constants.API_CALL_CONNECT_TIMEOUT,
                readTimeout = Constants.API_CALL_READ_TIMEOUT,
                writeTimeout = Constants.API_CALL_WRITE_TIMEOUT,
            )
        )
    }

    single<UserAgentProvider> {
        UserAgentNameProviderImpl(
            context = get(),
            versionCode = BuildConfig.VERSION_CODE.toString(),
            versionName = BuildConfig.VERSION_NAME,
        )
    }

    single<RequestModifierAnnotationProcessor> { CommonRequestModifierAnnotationProcessor() }

}