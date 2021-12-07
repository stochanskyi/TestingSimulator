package com.flaringapp.testingsimulator.admin.app.di

import com.flaringapp.testingsimulator.admin.data.network.AdminNetworkAdapter
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

val NetworkModule = module {

    val adapter : AdminNetworkAdapter by lazy {
        AdminNetworkAdapter(GlobalContext.get().get())
    }

}