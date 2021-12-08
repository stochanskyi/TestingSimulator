package com.flaringapp.testingsimulator.data.repository.topics

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.data.network.features.topics.TopicsDataSource
import com.flaringapp.testingsimulator.domain.features.topics.TopicsRepository
import com.flaringapp.testingsimulator.domain.features.topics.models.Topic

class TopicsRepositoryImpl(
    private val topicsDataSource: TopicsDataSource,
    private val topicsMapper: TopicsMapper
) : TopicsRepository {

    override suspend fun getTopics(): CallResult<List<Topic>> {
        val result = topicsDataSource.getTopics()

        return result.transform { topicsMapper.mapTopics(this) }
    }

}