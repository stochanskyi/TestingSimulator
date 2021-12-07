package com.flaringapp.testingsimulator.user.domain.login

import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.domain.features.auth.LoginUseCase
import com.flaringapp.testingsimulator.user.data.repository.AuthRepository
import com.flaringapp.testingsimulator.user.data.repository.UserDataRepository

class UserLoginUseCase(
    private val authRepository: AuthRepository,
    private val userDataRepository: UserDataRepository
) : LoginUseCase {

    override suspend fun login(
        email: String,
        password: String,
        rememberMe: Boolean,
    ): CallResultNothing {

        //TODO remember me

        val result = authRepository.login(email, password).doOnSuccess {
            userDataRepository.setToken(it.token)
        }

        return result.ignoreData()
    }

}