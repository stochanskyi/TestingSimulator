package com.flaringapp.testingsimulator.admin.app.di

import com.flaringapp.testingsimulator.admin.data.network.AdminNetworkAdapter
import com.flaringapp.testingsimulator.admin.data.network.features.auth.AdminAuthDataSource
import com.flaringapp.testingsimulator.admin.data.network.features.auth.AdminAuthDataSourceImpl
import com.flaringapp.testingsimulator.admin.data.network.features.tasks.AdminTasksDataSource
import com.flaringapp.testingsimulator.admin.data.network.features.tasks.AdminTasksDataSourceImpl
import com.flaringapp.testingsimulator.admin.data.network.features.tests.AdminTestsDataSource
import com.flaringapp.testingsimulator.admin.data.network.features.tests.AdminTestsDataSourceImpl
import com.flaringapp.testingsimulator.data.BuildConfig
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

val NetworkModule = module {

    val adapter : AdminNetworkAdapter by lazy {
        AdminNetworkAdapter(
            delegatedAdapter = GlobalContext.get().get(),
            apiUrl = BuildConfig.API_URL,
        )
    }

    single { adapter.createAuthApi() }

    single { adapter.createTestsApi() }

    single { adapter.createTaskApi() }

    single<AdminAuthDataSource> { AdminAuthDataSourceImpl(get()) }

    single<AdminTestsDataSource> { AdminTestsDataSourceImpl(get()) }

    single<AdminTasksDataSource> { AdminTasksDataSourceImpl(get()) }

}