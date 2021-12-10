package com.flaringapp.testingsimulator.admin.domain.login

import com.flaringapp.testingsimulator.admin.data.repository.AdminDataRepository
import com.flaringapp.testingsimulator.admin.data.repository.auth.AdminAuthRepository
import com.flaringapp.testingsimulator.admin.data.repository.profile.AdminProfileRepository
import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.domain.features.auth.LoginUseCase

class AdminLoginUseCase(
    private val authRepository: AdminAuthRepository,
    private val dataRepository: AdminDataRepository,
    private val profileRepository: AdminProfileRepository,
) : LoginUseCase {

    override suspend operator fun invoke(
        email: String,
        password: String,
        rememberMe: Boolean
    ): CallResultNothing {
        return authRepository.login(
            email = email,
            password = password
        )
            .doOnSuccess {
                dataRepository.token = it.token
                dataRepository.remember = rememberMe
            }
            .doOnSuccessSuspend {
                profileRepository.saveProfile(it.profile)
            }
            .ignoreData()
    }
}