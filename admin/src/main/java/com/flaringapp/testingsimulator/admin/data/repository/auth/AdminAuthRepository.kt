package com.flaringapp.testingsimulator.admin.data.repository.auth

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.admin.data.network.features.auth.request.AdminLoginRequest
import com.flaringapp.testingsimulator.admin.data.repository.auth.mappers.AdminLoginMapper
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
    private val loginMapper: AdminLoginMapper
) : AdminAuthRepository {

    override suspend fun login(
        email: String,
        password: String,
    ): CallResult<AdminLoginInfo> {
        val loginRequest = AdminLoginRequest(email, password)

        return authDataSource.login(loginRequest)
            .transform {
                loginMapper.map(this)
            }
    }
}