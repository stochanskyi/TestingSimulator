package com.flaringapp.testingsimulator.user.domain.profile

import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.domain.features.profile.LogoutUseCase
import com.flaringapp.testingsimulator.user.data.repository.UserDataRepository
import com.flaringapp.testingsimulator.user.data.repository.profile.UserProfileRepository

class UserLogoutUseCase(
    private val profileRepository: UserProfileRepository,
    private val dataRepository: UserDataRepository,
) : LogoutUseCase {

    override suspend fun invoke(): CallResultNothing {
        return profileRepository.logout()
            .doOnSuccessSuspend { dataRepository.token = null }
    }
}