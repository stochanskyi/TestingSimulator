package com.flaringapp.testingsimulator.user.domain.profile

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.features.profile.EditProfileUseCase
import com.flaringapp.testingsimulator.user.data.repository.profile.UserProfileRepository

class EditUserProfileUseCase(
    private val profileRepository: UserProfileRepository
) : EditProfileUseCase<EditUserProfile, UserProfile> {

    override suspend fun invoke(profile: EditUserProfile): CallResult<UserProfile> {
        return profileRepository.editProfile(profile)
    }
}