package com.flaringapp.testingsimulator.admin.data.network

import com.flaringapp.testingsimulator.admin.data.network.features.auth.AdminAuthApi
import com.flaringapp.testingsimulator.core.data.network.adapter.DelegatedNetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.NetworkAdapter

class AdminNetworkAdapter(
    override val delegatedAdapter: NetworkAdapter,
) : DelegatedNetworkAdapter() {

    private val client = createClient("http://test")

    fun createAuthApi(): AdminAuthApi = client.create(AdminAuthApi::class.java)

}