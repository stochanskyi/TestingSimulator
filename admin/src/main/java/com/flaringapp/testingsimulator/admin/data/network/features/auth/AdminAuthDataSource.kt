package com.flaringapp.testingsimulator.admin.data.network.features.auth

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.network.base.validate
import com.flaringapp.testingsimulator.admin.data.network.features.auth.models.request.AdminLoginRequest
import com.flaringapp.testingsimulator.admin.data.network.features.auth.models.response.AdminLoginResponse

interface AdminAuthDataSource {
    suspend fun login(loginRequest: AdminLoginRequest): CallResult<AdminLoginResponse>
}

class AdminAuthDataSourceImpl(
    private val authApi: AdminAuthApi
) : AdminAuthDataSource {

    override suspend fun login(loginRequest: AdminLoginRequest): CallResult<AdminLoginResponse> {
        return authApi.login(loginRequest).validate()
    }

}