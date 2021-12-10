package com.flaringapp.testingsimulator.domain.features.topics

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.features.topics.models.Topic

class GetTopicsUseCase(
    private val topicsRepository: TopicsRepository
) {

    suspend operator fun invoke(): CallResult<List<Topic>> {
        return topicsRepository.getTopics()
    }
}