package com.flaringapp.testingsimulator.user.data.network.features.tasks

import com.flaringapp.testingsimulator.core.data.network.base.ApiResponse
import com.flaringapp.testingsimulator.user.data.network.features.tasks.request.StartTestRequest
import com.flaringapp.testingsimulator.user.data.network.features.tasks.request.TaskAnswerRequest
import com.flaringapp.testingsimulator.user.data.network.features.tasks.response.UserTaskResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserTasksApi {

    @POST("start_test")
    suspend fun startTest(@Body request: StartTestRequest): ApiResponse<UserTaskResponse>

    @GET("answer")
    suspend fun getTask(@Query("testId") testId: Int): ApiResponse<UserTaskResponse>

    @POST("answer")
    suspend fun answerTask(@Body request: TaskAnswerRequest): ApiResponse<UserTaskResponse?>

}