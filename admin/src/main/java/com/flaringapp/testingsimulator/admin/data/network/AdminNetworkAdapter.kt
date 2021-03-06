package com.flaringapp.testingsimulator.admin.data.network

import com.flaringapp.testingsimulator.admin.data.network.features.auth.AdminAuthApi
import com.flaringapp.testingsimulator.admin.data.network.features.tasks.AdminTasksApi
import com.flaringapp.testingsimulator.admin.data.network.features.tests.AdminTestsApi
import com.flaringapp.testingsimulator.core.data.network.adapter.DelegatedNetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.NetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.withModifiers
import com.flaringapp.testingsimulator.data.network.modifiers.token.RequestTokenAppender

class AdminNetworkAdapter(
    override val delegatedAdapter: NetworkAdapter,
    apiUrl: String,
) : DelegatedNetworkAdapter() {

    private val client = withModifiers(RequestTokenAppender())
        .createClient(apiUrl)

    fun createAuthApi(): AdminAuthApi = client.create(AdminAuthApi::class.java)

    fun createTestsApi(): AdminTestsApi = client.create(AdminTestsApi::class.java)

    fun createTaskApi(): AdminTasksApi = client.create(AdminTasksApi::class.java)
}