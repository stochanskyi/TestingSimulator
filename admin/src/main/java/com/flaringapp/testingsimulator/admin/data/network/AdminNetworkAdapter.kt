package com.flaringapp.testingsimulator.admin.data.network

import com.flaringapp.testingsimulator.admin.data.network.features.auth.AdminAuthApi
import com.flaringapp.testingsimulator.admin.data.network.features.tests.AdminTestsApi
import com.flaringapp.testingsimulator.core.data.network.adapter.DelegatedNetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.NetworkAdapter

class AdminNetworkAdapter(
    override val delegatedAdapter: NetworkAdapter,
    apiUrl: String,
) : DelegatedNetworkAdapter() {

    private val client = createClient(apiUrl)

    fun createAuthApi(): AdminAuthApi = client.create(AdminAuthApi::class.java)

    fun createTestsApi(): AdminTestsApi = client.create(AdminTestsApi::class.java)

}