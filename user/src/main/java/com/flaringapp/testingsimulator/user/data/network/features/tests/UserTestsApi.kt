package com.flaringapp.testingsimulator.user.data.network.features.tests

import com.flaringapp.testingsimulator.core.data.network.base.ApiResponse
import com.flaringapp.testingsimulator.core.data.network.base.ApiResponseList
import com.flaringapp.testingsimulator.user.data.network.features.tests.response.UserTestDetailsResponse
import com.flaringapp.testingsimulator.user.data.network.features.tests.response.UserTestResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserTestsApi {

    @GET("Test/by-topic-id/{topicId}")
    suspend fun getTests(
        @Path("topicId") topicId: Int
    ): ApiResponseList<UserTestResponse>

    @GET("Test/{testId}")
    suspend fun getTestDetails(
        @Path("testId") testId: Int
    ): ApiResponse<UserTestDetailsResponse>

}