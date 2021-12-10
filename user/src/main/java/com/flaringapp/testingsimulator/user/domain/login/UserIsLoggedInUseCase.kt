package com.flaringapp.testingsimulator.user.domain.login

import com.flaringapp.testingsimulator.domain.features.auth.IsLoggedInUseCase
import com.flaringapp.testingsimulator.user.data.repository.profile.UserProfileRepository

class UserIsLoggedInUseCase(
    private val profileRepository: UserProfileRepository
): IsLoggedInUseCase {

    override fun invoke(): Boolean {
        return profileRepository.isLoggedIn()
    }
}