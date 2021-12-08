package com.flaringapp.testingsimulator.user.domain.profile

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.features.profile.GetProfileUseCase
import com.flaringapp.testingsimulator.user.data.repository.profile.UserProfileRepository

class GetUserProfileUseCase(
    private val profileRepository: UserProfileRepository,
) : GetProfileUseCase<UserProfile> {

    override suspend fun invoke(): CallResult<UserProfile> {
        return profileRepository.getProfile()
    }
}