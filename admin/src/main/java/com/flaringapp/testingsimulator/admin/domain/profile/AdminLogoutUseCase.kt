package com.flaringapp.testingsimulator.admin.domain.profile

import com.flaringapp.testingsimulator.admin.data.repository.AdminDataRepository
import com.flaringapp.testingsimulator.admin.data.repository.profile.AdminProfileRepository
import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.domain.features.profile.LogoutUseCase

class AdminLogoutUseCase(
    private val profileRepository: AdminProfileRepository,
    private val dataRepository: AdminDataRepository,
) : LogoutUseCase {

    override suspend fun invoke(): CallResultNothing {
        return profileRepository.logout()
            .doOnSuccessSuspend { dataRepository.token = null }
    }
}