package com.flaringapp.testingsimulator.app.di

import com.flaringapp.testingsimulator.data.network.RetrofitAdapter
import com.flaringapp.testingsimulator.data.network.common.useragent.UserAgentNameProviderImpl
import com.flaringapp.testingsimulator.data.network.common.useragent.UserAgentProvider
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

val NetworkModule = module {

    val adapter: RetrofitAdapter by lazy {
        val userAgentProvider: UserAgentProvider = GlobalContext.get().get()

        RetrofitAdapter(userAgentProvider, "")
    }

    single<UserAgentProvider> { UserAgentNameProviderImpl(get()) }

}