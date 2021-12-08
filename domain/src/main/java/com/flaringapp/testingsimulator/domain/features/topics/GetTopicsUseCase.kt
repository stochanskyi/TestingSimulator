package com.flaringapp.testingsimulator.domain.features.topics

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.features.topics.models.Topic

class GetTopicsUseCase {
    suspend operator fun invoke(): CallResult<List<Topic>> {
        return CallResult.Success(listOf(Topic(1, "Testing", true, 1), Topic(1, "Dev", false, 1)))
    }
}