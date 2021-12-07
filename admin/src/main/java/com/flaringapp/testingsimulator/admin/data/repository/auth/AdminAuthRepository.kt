package com.flaringapp.testingsimulator.admin.data.repository.auth

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.admin.data.network.features.auth.models.request.AdminLoginRequest
import com.flaringapp.testingsimulator.admin.data.repository.auth.mappers.AdminLoginResponseMapper
import com.flaringapp.testingsimulator.admin.data.repository.auth.models.AdminLoginInfo
import com.flaringapp.testingsimulator.admin.data.network.features.auth.AdminAuthDataSource

interface AdminAuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): CallResult<AdminLoginInfo>

}

class AdminAuthRepositoryImpl(
    private val authDataSource: AdminAuthDataSource,
    private val loginResponseMapper: AdminLoginResponseMapper
) : AdminAuthRepository {

    override suspend fun login(
        email: String,
        password: String,
    ): CallResult<AdminLoginInfo> {
        val loginRequest = AdminLoginRequest(email, password)

        return authDataSource.login(loginRequest).transform {
            loginResponseMapper.map(this)
        }
    }

}