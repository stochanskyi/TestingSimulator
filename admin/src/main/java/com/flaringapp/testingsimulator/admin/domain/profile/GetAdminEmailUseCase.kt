package com.flaringapp.testingsimulator.admin.domain.profile

import com.flaringapp.testingsimulator.admin.data.repository.profile.AdminProfileRepository
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.features.auth.GetLastEmailUseCase

class GetAdminEmailUseCase(
    private val profileRepository: AdminProfileRepository
) : GetLastEmailUseCase {

    override suspend fun invoke(): CallResult<String> {
        return profileRepository.getLastEmail()
    }
}