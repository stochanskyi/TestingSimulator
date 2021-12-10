package com.flaringapp.testingsimulator.data

import com.flaringapp.testingsimulator.core.data.network.adapter.NetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.RetrofitAdapter
import com.flaringapp.testingsimulator.data.network.SharedNetworkAdapter
import com.flaringapp.testingsimulator.data.network.features.edit_profile.EditProfileDataSource
import com.flaringapp.testingsimulator.data.network.features.edit_profile.EditProfileDataSourceImpl
import com.flaringapp.testingsimulator.data.network.features.profiles_statistics.ProfileStatisticsDataSource
import com.flaringapp.testingsimulator.data.network.features.profiles_statistics.ProfileStatisticsDataSourceImpl
import com.flaringapp.testingsimulator.data.network.features.topics.TopicsDataSource
import com.flaringapp.testingsimulator.data.network.features.topics.TopicsDataSourceImpl
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
        SharedNetworkAdapter(GlobalContext.get().get(), BuildConfig.API_URL)
    }

    single { adapter.createProfileStatisticsApi() }
    single<ProfileStatisticsDataSource> { ProfileStatisticsDataSourceImpl(get()) }

    single { adapter.createEditProfileApi() }
    single<EditProfileDataSource> { EditProfileDataSourceImpl(get()) }

    single { adapter.createTopicsApi() }
    single<TopicsDataSource> { TopicsDataSourceImpl(get()) }

}