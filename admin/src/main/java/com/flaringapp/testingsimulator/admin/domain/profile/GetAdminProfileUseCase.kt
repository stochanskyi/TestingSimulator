package com.flaringapp.testingsimulator.admin.domain.profile

import com.flaringapp.testingsimulator.admin.data.repository.profile.AdminProfileRepository
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.features.profile.GetProfileUseCase

class GetAdminProfileUseCase(
    private val adminProfileRepository: AdminProfileRepository,
) : GetProfileUseCase<AdminProfile> {

    override suspend fun invoke(): CallResult<AdminProfile> {
        return adminProfileRepository.getProfile()
    }
}