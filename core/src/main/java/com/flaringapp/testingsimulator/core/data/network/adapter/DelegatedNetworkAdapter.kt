package com.flaringapp.testingsimulator.core.data.network.adapter

import com.flaringapp.testingsimulator.core.data.network.common.modifier.RequestModifier
import retrofit2.Retrofit

abstract class DelegatedNetworkAdapter : NetworkAdapter {

    protected abstract val delegatedAdapter: NetworkAdapter

    override fun createClient(
        baseUrl: String,
        staticModifiers: List<RequestModifier>
    ): Retrofit {
        return delegatedAdapter.createClient(baseUrl, staticModifiers)
    }
}