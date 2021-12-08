package com.flaringapp.testingsimulator.user.domain.login

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.domain.features.auth.LoginUseCase
import com.flaringapp.testingsimulator.user.data.repository.auth.UserAuthRepository
import com.flaringapp.testingsimulator.user.data.repository.UserDataRepository

class UserLoginUseCase(
    private val authRepository: UserAuthRepository,
    private val dataRepository: UserDataRepository,
) : LoginUseCase {

    override suspend operator fun invoke(
        email: String,
        password: String,
        rememberMe: Boolean,
    ): CallResultNothing {
        //TODO remember me

        //TODO remove mocked answer

        return CallResult.Success(Unit)

        return authRepository.login(
            email = email,
            password = password
        ).doOnSuccess {
            dataRepository.token = it.token
        }.ignoreData()
    }
}