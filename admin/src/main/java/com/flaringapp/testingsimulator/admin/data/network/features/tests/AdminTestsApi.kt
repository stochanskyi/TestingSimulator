package com.flaringapp.testingsimulator.admin.data.network.features.tests

import com.flaringapp.testingsimulator.admin.data.network.features.tests.request.ChangeTestStateRequest
import com.flaringapp.testingsimulator.admin.data.network.features.tests.request.CreateTestRequest
import com.flaringapp.testingsimulator.admin.data.network.features.tests.response.AdminTestResponse
import com.flaringapp.testingsimulator.admin.data.network.features.tests.response.AdminTestWithStatisticsModel
import com.flaringapp.testingsimulator.core.data.network.base.ApiResponse
import com.flaringapp.testingsimulator.core.data.network.base.ApiResponseList
import retrofit2.http.*

interface AdminTestsApi {

    @GET("Test/by-topic-id/{topicId}")
    suspend fun getTests(
        @Path("topicId") topicId: Int
    ): ApiResponseList<AdminTestResponse>

    @GET("Test/{testId}")
    suspend fun getTest(
        @Path("testId") testId: Int
    ): ApiResponse<AdminTestWithStatisticsModel>

    @POST("Test")
    suspend fun createTest(@Body request: CreateTestRequest): ApiResponse<AdminTestResponse>

    @PUT("Test/state")
    suspend fun changeTestState(
        @Body request: ChangeTestStateRequest
    ): ApiResponse<AdminTestWithStatisticsModel>

}