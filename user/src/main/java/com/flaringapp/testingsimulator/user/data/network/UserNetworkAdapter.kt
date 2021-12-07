package com.flaringapp.testingsimulator.user.data.network

import com.flaringapp.testingsimulator.core.data.network.adapter.DelegatedNetworkAdapter
import com.flaringapp.testingsimulator.core.data.network.adapter.NetworkAdapter

class UserNetworkAdapter(
    override val delegatedAdapter: NetworkAdapter,
) : DelegatedNetworkAdapter()