package com.flaringapp.testingsimulator.app.di

import com.flaringapp.testingsimulator.data.network.adapter.NetworkAdapter
import com.flaringapp.testingsimulator.data.network.adapter.RetrofitAdapter
import com.flaringapp.testingsimulator.data.network.common.useragent.UserAgentNameProviderImpl
import com.flaringapp.testingsimulator.data.network.common.useragent.UserAgentProvider
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

val NetworkModule = module {

    val adapter: NetworkAdapter by lazy {
        val userAgentProvider: UserAgentProvider = GlobalContext.get().get()

        RetrofitAdapter(userAgentProvider, GlobalContext.get().getOrNull())
    }

    single<UserAgentProvider> { UserAgentNameProviderImpl(get()) }

}