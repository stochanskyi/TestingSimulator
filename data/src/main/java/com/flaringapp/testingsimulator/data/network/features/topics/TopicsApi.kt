package com.flaringapp.testingsimulator.data.network.features.topics

import com.flaringapp.testingsimulator.core.data.network.base.ApiResponse
import com.flaringapp.testingsimulator.data.network.features.topics.response.TopicResponse
import retrofit2.http.GET

interface TopicsApi {

    @GET("Module")
    suspend fun getTopics(): ApiResponse<List<TopicResponse>>

}