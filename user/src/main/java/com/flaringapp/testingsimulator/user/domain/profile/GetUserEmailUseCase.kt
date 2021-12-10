package com.flaringapp.testingsimulator.user.domain.profile

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.features.auth.GetLastEmailUseCase
import com.flaringapp.testingsimulator.user.data.repository.profile.UserProfileRepository

class GetUserEmailUseCase(
    private val profileRepository: UserProfileRepository
) : GetLastEmailUseCase {

    override suspend fun invoke(): CallResult<String> {
        return profileRepository.getLastEmail()
    }
}