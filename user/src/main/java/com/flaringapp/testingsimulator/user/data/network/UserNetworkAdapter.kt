package com.flaringapp.testingsimulator.user.data.network

import com.flaringapp.testingsimulator.core.data.network.adapter.DelegatedNetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.NetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.withModifiers
import com.flaringapp.testingsimulator.data.network.modifiers.token.RequestTokenAppender
import com.flaringapp.testingsimulator.user.data.network.features.auth.UserAuthApi
import com.flaringapp.testingsimulator.user.data.network.features.tasks.UserTasksApi
import com.flaringapp.testingsimulator.user.data.network.features.tests.UserTestsApi

class UserNetworkAdapter(
    override val delegatedAdapter: NetworkAdapter,
    apiUrl: String,
) : DelegatedNetworkAdapter() {

    private val client = withModifiers(RequestTokenAppender())
        .createClient(apiUrl)

    fun createAuthApi(): UserAuthApi = client.create(UserAuthApi::class.java)

    fun createTestsApi(): UserTestsApi = client.create(UserTestsApi::class.java)

    fun createTasksApi(): UserTasksApi = client.create(UserTasksApi::class.java)

}