package com.flaringapp.testingsimulator.domain.features.topics

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.features.topics.models.Topic

class GetTopicsUseCase(
    private val topicsRepository: TopicsRepository
) {

    suspend operator fun invoke(): CallResult<List<Topic>> {
        //TODO mock
//        return topicsRepository.getTopics()

        return CallResult.Success(
            listOf(
                Topic(1, "Testing", true, 2),
                Topic(2, "Design", true, 1)
            )
        )
    }
}