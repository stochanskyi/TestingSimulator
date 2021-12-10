package com.flaringapp.testingsimulator.domain.features.auth

import com.flaringapp.testingsimulator.core.data.common.call.CallResult

interface GetLastEmailUseCase {
    suspend operator fun invoke(): CallResult<String>
}