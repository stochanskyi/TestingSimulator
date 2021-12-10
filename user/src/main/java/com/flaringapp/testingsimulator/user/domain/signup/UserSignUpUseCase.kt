package com.flaringapp.testingsimulator.user.domain.signup

import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.user.data.repository.auth.UserAuthRepository
import com.flaringapp.testingsimulator.user.data.repository.UserDataRepository
import com.flaringapp.testingsimulator.user.data.repository.profile.UserProfileRepository
import com.flaringapp.testingsimulator.user.domain.signup.models.UserRegistrationData

class UserSignUpUseCase(
    private val authRepository: UserAuthRepository,
    private val dataRepository: UserDataRepository,
    private val profileRepository: UserProfileRepository,
) {

    suspend fun signUp(userRegistrationData: UserRegistrationData): CallResultNothing {
        return authRepository.signUp(userRegistrationData)
            .doOnSuccess {
                dataRepository.token = it.token
                dataRepository.remember = true
            }
            .doOnSuccessSuspend {
                profileRepository.saveProfile(it.profile)
            }
            .ignoreData()
    }

}