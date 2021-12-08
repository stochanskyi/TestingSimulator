package com.flaringapp.testingsimulator.data

import com.flaringapp.testingsimulator.core.data.network.adapter.NetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.RetrofitAdapter
import org.koin.dsl.module

val NetworkModule = module {

    single<NetworkAdapter> {
        RetrofitAdapter(
            userAgentProvider = get(),
            modifierAnnotationProcessor = getOrNull(),
            config = getOrNull()
        )
    }

}