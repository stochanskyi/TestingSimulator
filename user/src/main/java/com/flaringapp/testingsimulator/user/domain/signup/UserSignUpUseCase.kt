package com.flaringapp.testingsimulator.user.domain.signup

import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.user.data.repository.auth.AuthRepository
import com.flaringapp.testingsimulator.user.data.repository.UserDataRepository
import com.flaringapp.testingsimulator.user.domain.signup.models.UserRegistrationData

class UserSignUpUseCase(
    private val authRepository: AuthRepository,
    private val userDataRepository: UserDataRepository
) {

    suspend fun signUp(userRegistrationData: UserRegistrationData): CallResultNothing {
        return authRepository.signUp(userRegistrationData)
            .doOnSuccess { userDataRepository.token = it.token }
            .ignoreData()
    }

}