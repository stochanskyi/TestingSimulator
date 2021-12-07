package com.flaringapp.testingsimulator.user.data.network

import com.flaringapp.testingsimulator.core.data.network.adapter.DelegatedNetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.NetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.withModifiers
import com.flaringapp.testingsimulator.user.data.network.features.auth.AuthApi

class UserNetworkAdapter(
    override val delegatedAdapter: NetworkAdapter,
) : DelegatedNetworkAdapter() {

    private val client = createClient("http://test")

    fun createAuthApi(): AuthApi = client.create(AuthApi::class.java)

}