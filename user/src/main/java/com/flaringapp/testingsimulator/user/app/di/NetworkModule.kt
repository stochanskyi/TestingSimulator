package com.flaringapp.testingsimulator.user.app.di

import com.flaringapp.testingsimulator.data.BuildConfig
import com.flaringapp.testingsimulator.user.data.network.UserNetworkAdapter
import com.flaringapp.testingsimulator.user.data.network.features.auth.UserAuthDataSource
import com.flaringapp.testingsimulator.user.data.network.features.auth.UserAuthDataSourceImpl
import com.flaringapp.testingsimulator.user.data.network.features.tests.UserTestsDataSource
import com.flaringapp.testingsimulator.user.data.network.features.tests.UserTestsDataSourceImpl
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

val NetworkModule = module {

    val adapter : UserNetworkAdapter by lazy {
        UserNetworkAdapter(
            delegatedAdapter = GlobalContext.get().get(),
            apiUrl = BuildConfig.API_URL,
        )
    }

    single { adapter.createAuthApi() }

    single { adapter.createTestsApi() }

    single<UserAuthDataSource> { UserAuthDataSourceImpl(get()) }

    single<UserTestsDataSource> { UserTestsDataSourceImpl(get()) }

}