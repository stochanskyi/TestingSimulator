package com.flaringapp.testingsimulator.user.data.network

import com.flaringapp.testingsimulator.core.data.network.adapter.DelegatedNetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.NetworkAdapter
import com.flaringapp.testingsimulator.user.data.network.features.auth.UserAuthApi
import com.flaringapp.testingsimulator.user.data.network.features.tests.UserTestsApi

class UserNetworkAdapter(
    override val delegatedAdapter: NetworkAdapter,
) : DelegatedNetworkAdapter() {

    private val client = createClient("http://test")

    fun createAuthApi(): UserAuthApi = client.create(UserAuthApi::class.java)

    fun createTestsApi(): UserTestsApi = client.create(UserTestsApi::class.java)

}