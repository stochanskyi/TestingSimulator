package com.flaringapp.testingsimulator.data.network

import com.flaringapp.testingsimulator.core.data.network.adapter.DelegatedNetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.NetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.withModifiers
import com.flaringapp.testingsimulator.data.network.features.edit_profile.EditProfileApi
import com.flaringapp.testingsimulator.data.network.features.profiles_statistics.ProfileStatisticsApi
import com.flaringapp.testingsimulator.data.network.features.topics.TopicsApi
import com.flaringapp.testingsimulator.data.network.modifiers.token.RequestTokenAppender

class SharedNetworkAdapter(
    override val delegatedAdapter: NetworkAdapter,
    apiUrl: String,
) : DelegatedNetworkAdapter() {

    private val client = withModifiers(RequestTokenAppender())
        .createClient(apiUrl)

    fun createProfileStatisticsApi(): ProfileStatisticsApi {
        return client.create(ProfileStatisticsApi::class.java)
    }

    fun createEditProfileApi(): EditProfileApi {
        return client.create(EditProfileApi::class.java)
    }

    fun createTopicsApi(): TopicsApi {
        return client.create(TopicsApi::class.java)
    }

}