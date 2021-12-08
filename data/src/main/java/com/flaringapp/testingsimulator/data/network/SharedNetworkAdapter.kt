package com.flaringapp.testingsimulator.data.network

import com.flaringapp.testingsimulator.core.data.network.adapter.DelegatedNetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.NetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.withModifiers
import com.flaringapp.testingsimulator.data.network.features.profiles_tatistics.ProfileStatisticsApi
import com.flaringapp.testingsimulator.data.network.features.topics.TopicsApi
import com.flaringapp.testingsimulator.data.network.modifiers.token.RequestTokenAppender

class SharedNetworkAdapter(
    override val delegatedAdapter: NetworkAdapter
) : DelegatedNetworkAdapter() {

    private val client = withModifiers(RequestTokenAppender()).createClient("http://test")

    fun createProfileStatisticsApi(): ProfileStatisticsApi {
        return client.create(ProfileStatisticsApi::class.java)
    }

    fun createTopicsApi(): TopicsApi {
        return client.create(TopicsApi::class.java)
    }

}