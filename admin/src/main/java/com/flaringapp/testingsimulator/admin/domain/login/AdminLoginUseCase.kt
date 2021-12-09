package com.flaringapp.testingsimulator.admin.domain.login

import com.flaringapp.testingsimulator.admin.data.repository.auth.AdminAuthRepository
import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.domain.features.auth.LoginUseCase
import com.flaringapp.testingsimulator.admin.data.repository.AdminDataRepository
import com.flaringapp.testingsimulator.admin.data.repository.profile.AdminProfileRepository
import com.flaringapp.testingsimulator.core.data.common.call.CallResult

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
        //TODO remember me

        return CallResult.Success(Unit)

        return authRepository.login(
            email = email,
            password = password
        )
            .doOnSuccess {
                dataRepository.token = it.token
            }
            .doOnSuccessSuspend {
                profileRepository.saveProfile(it.profile)
            }
            .ignoreData()
    }
}