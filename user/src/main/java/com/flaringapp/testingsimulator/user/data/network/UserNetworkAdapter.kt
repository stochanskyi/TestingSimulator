package com.flaringapp.testingsimulator.user.data.network

import com.flaringapp.testingsimulator.core.data.network.adapter.DelegatedNetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.NetworkAdapter
import com.flaringapp.testingsimulator.user.data.network.features.auth.UserAuthApi
import com.flaringapp.testingsimulator.user.data.network.features.tests.UserTestsApi

class UserNetworkAdapter(
    override val delegatedAdapter: NetworkAdapter,
    apiUrl: String,
) : DelegatedNetworkAdapter() {

    private val client = createClient(apiUrl)

    fun createAuthApi(): UserAuthApi = client.create(UserAuthApi::class.java)

    fun createTestsApi(): UserTestsApi = client.create(UserTestsApi::class.java)

}