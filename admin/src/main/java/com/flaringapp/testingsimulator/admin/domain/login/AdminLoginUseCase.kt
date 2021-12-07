package com.flaringapp.testingsimulator.admin.domain.login

import com.flaringapp.testingsimulator.admin.data.repository.auth.AdminAuthRepository
import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.domain.features.auth.LoginUseCase
import com.flaringapp.testingsimulator.admin.data.repository.AdminDataRepository

class AdminLoginUseCase(
    private val authRepository: AdminAuthRepository,
    private val userDataRepository: AdminDataRepository
) : LoginUseCase {
    override suspend fun login(
        email: String,
        password: String,
        rememberMe: Boolean
    ): CallResultNothing {
        //TODO remember me

        val result = authRepository.login(email, password).doOnSuccess {
            userDataRepository.setToken(it.token)
        }

        return result.ignoreData()
    }
}