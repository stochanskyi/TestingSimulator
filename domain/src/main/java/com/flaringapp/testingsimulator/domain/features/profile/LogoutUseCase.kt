package com.flaringapp.testingsimulator.domain.features.profile

import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing

interface LogoutUseCase {
    suspend operator fun invoke(): CallResultNothing
}