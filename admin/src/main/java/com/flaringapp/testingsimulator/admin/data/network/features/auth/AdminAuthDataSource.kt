package com.flaringapp.testingsimulator.admin.data.network.features.auth

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.network.base.validate
import com.flaringapp.testingsimulator.admin.data.network.features.auth.request.AdminLoginRequest
import com.flaringapp.testingsimulator.admin.data.network.features.auth.response.AdminLoginResponse

interface AdminAuthDataSource {

    suspend fun login(
        request: AdminLoginRequest
    ): CallResult<AdminLoginResponse>

}

class AdminAuthDataSourceImpl(
    private val authApi: AdminAuthApi
) : AdminAuthDataSource {

    override suspend fun login(
        request: AdminLoginRequest
    ): CallResult<AdminLoginResponse> {
        return authApi.login(request).validate()
    }

}