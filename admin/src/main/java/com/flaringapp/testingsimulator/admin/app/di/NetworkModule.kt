package com.flaringapp.testingsimulator.admin.app.di

import com.flaringapp.testingsimulator.admin.data.network.AdminNetworkAdapter
import com.flaringapp.testingsimulator.admin.data.network.features.auth.AdminAuthDataSource
import com.flaringapp.testingsimulator.admin.data.network.features.auth.AdminAuthDataSourceImpl
import com.flaringapp.testingsimulator.admin.data.network.features.tests.AdminTestsDataSource
import com.flaringapp.testingsimulator.admin.data.network.features.tests.AdminTestsDataSourceImpl
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

val NetworkModule = module {

    val adapter : AdminNetworkAdapter by lazy {
        AdminNetworkAdapter(GlobalContext.get().get())
    }

    single { adapter.createAuthApi() }

    single { adapter.createTestsApi() }

    single<AdminAuthDataSource> { AdminAuthDataSourceImpl(get()) }

    single<AdminTestsDataSource> { AdminTestsDataSourceImpl(get()) }

}