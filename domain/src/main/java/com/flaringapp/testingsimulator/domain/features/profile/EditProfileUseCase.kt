package com.flaringapp.testingsimulator.domain.features.profile

import com.flaringapp.testingsimulator.core.data.common.call.CallResult

interface EditProfileUseCase<I : EditProfile, O : Profile> {
    suspend operator fun invoke(profile: I): CallResult<O>
}