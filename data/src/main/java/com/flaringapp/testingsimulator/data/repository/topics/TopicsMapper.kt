package com.flaringapp.testingsimulator.data.repository.topics

import com.flaringapp.testingsimulator.data.network.features.topics.response.TopicResponse
import com.flaringapp.testingsimulator.domain.features.topics.models.Topic

interface TopicsMapper {

    fun mapTopic(dto: TopicResponse): Topic

    fun mapTopics(dto: List<TopicResponse>): List<Topic>

}

class TopicsMapperImpl : TopicsMapper {

    override fun mapTopic(dto: TopicResponse): Topic {
        return Topic(
            id = dto.id,
            name = dto.name,
            enabled = dto.enabled,
            emojiId = dto.emojiId
        )
    }

    override fun mapTopics(dto: List<TopicResponse>): List<Topic> {
        return dto.map { mapTopic(it) }
    }

}