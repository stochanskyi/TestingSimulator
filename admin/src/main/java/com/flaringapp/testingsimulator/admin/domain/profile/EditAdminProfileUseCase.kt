package com.flaringapp.testingsimulator.admin.domain.profile

import com.flaringapp.testingsimulator.admin.data.repository.profile.AdminProfileRepository
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.features.profile.EditProfileUseCase

class EditAdminProfileUseCase(
    private val profileRepository: AdminProfileRepository
) : EditProfileUseCase<EditAdminProfile, AdminProfile> {

    override suspend fun invoke(profile: EditAdminProfile): CallResult<AdminProfile> {
        return profileRepository.editProfile(profile)
    }
}