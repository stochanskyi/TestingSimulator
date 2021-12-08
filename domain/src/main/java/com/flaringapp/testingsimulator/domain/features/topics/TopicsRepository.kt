package com.flaringapp.testingsimulator.domain.features.topics

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.features.topics.models.Topic

interface TopicsRepository {

    suspend fun getTopics(): CallResult<List<Topic>>

}