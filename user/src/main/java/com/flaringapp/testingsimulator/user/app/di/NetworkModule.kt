package com.flaringapp.testingsimulator.user.app.di

import com.flaringapp.testingsimulator.user.data.network.UserNetworkAdapter
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

val NetworkModule = module {

    val adapter : UserNetworkAdapter by lazy {
        UserNetworkAdapter(GlobalContext.get().get())
    }

}