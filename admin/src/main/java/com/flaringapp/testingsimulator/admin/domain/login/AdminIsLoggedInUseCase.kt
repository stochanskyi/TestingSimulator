package com.flaringapp.testingsimulator.admin.domain.login

import com.flaringapp.testingsimulator.admin.data.repository.profile.AdminProfileRepository
import com.flaringapp.testingsimulator.domain.features.auth.IsLoggedInUseCase

class AdminIsLoggedInUseCase(
    private val profileRepository: AdminProfileRepository
): IsLoggedInUseCase {

    override fun invoke(): Boolean {
        return profileRepository.isLoggedIn()
    }
}