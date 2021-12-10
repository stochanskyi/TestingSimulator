package com.flaringapp.testingsimulator.user.data.network.features.tasks

import com.flaringapp.testingsimulator.core.data.network.base.ApiResponse
import com.flaringapp.testingsimulator.user.data.network.features.tasks.request.StartTestRequest
import com.flaringapp.testingsimulator.user.data.network.features.tasks.request.UserTaskAnswerRequest
import com.flaringapp.testingsimulator.user.data.network.features.tasks.response.UserTaskResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserTasksApi {

    @GET("answer")
    suspend fun getTask(@Query("testId") testId: Int): ApiResponse<UserTaskResponse>

    @POST("answer")
    suspend fun answerTask(@Body request: UserTaskAnswerRequest): ApiResponse<UserTaskResponse?>

}