package com.flaringapp.app.di

import com.flaringapp.data.network.RetrofitAdapter
import com.flaringapp.data.network.common.useragent.UserAgentNameProviderImpl
import com.flaringapp.data.network.common.useragent.UserAgentProvider
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

val NetworkModule = module {

    val adapter: RetrofitAdapter by lazy {
        val userAgentProvider: UserAgentProvider = GlobalContext.get().get()

        RetrofitAdapter(userAgentProvider, "")
    }

    single<UserAgentProvider> { UserAgentNameProviderImpl(get()) }

}