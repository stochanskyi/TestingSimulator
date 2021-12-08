package com.flaringapp.testingsimulator.domain.features.profile

import com.flaringapp.testingsimulator.core.data.common.call.CallResult

interface GetProfileUseCase<T : Profile> {
    suspend operator fun invoke(): CallResult<T>
}