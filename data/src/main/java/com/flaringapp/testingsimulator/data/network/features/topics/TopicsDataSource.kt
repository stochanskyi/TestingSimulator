package com.flaringapp.testingsimulator.data.network.features.topics

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.network.base.validateList
import com.flaringapp.testingsimulator.data.network.features.topics.response.TopicResponse

interface TopicsDataSource {
    suspend fun getTopics(): CallResult<List<TopicResponse>>
}

class TopicsDataSourceImpl(
    private val api: TopicsApi
): TopicsDataSource {

    override suspend fun getTopics(): CallResult<List<TopicResponse>> {
        return api.getTopics().validateList()
    }

}