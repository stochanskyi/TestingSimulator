package com.flaringapp.testingsimulator.data

import com.flaringapp.testingsimulator.core.data.network.adapter.NetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.RetrofitAdapter
import com.flaringapp.testingsimulator.data.network.SharedNetworkAdapter
import com.flaringapp.testingsimulator.data.network.features.profiles_tatistics.ProfileStatisticsDataSource
import com.flaringapp.testingsimulator.data.network.features.profiles_tatistics.ProfileStatisticsDataSourceImpl
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

val NetworkModule = module {

    single<NetworkAdapter> {
        RetrofitAdapter(
            userAgentProvider = get(),
            modifierAnnotationProcessor = getOrNull(),
            config = getOrNull()
        )
    }

    val adapter: SharedNetworkAdapter by lazy {
        SharedNetworkAdapter(GlobalContext.get().get())
    }

    single { adapter.createProfileStatisticsApi() }
    single<ProfileStatisticsDataSource> { ProfileStatisticsDataSourceImpl(get()) }

}