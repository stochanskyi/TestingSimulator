package com.flaringapp.testingsimulator.user.domain.login

import com.flaringapp.testingsimulator.domain.features.auth.IsLoggedInUseCase
import com.flaringapp.testingsimulator.user.data.repository.UserDataRepository

class UserIsLoggedInUseCase(
    private val dataRepository: UserDataRepository,
): IsLoggedInUseCase {

    override fun invoke(): Boolean {
        return dataRepository.remember && dataRepository.token != null
    }
}