package com.flaringapp.testingsimulator.user.data.network.features.tasks

import com.flaringapp.testingsimulator.core.data.network.base.ApiResponse
import com.flaringapp.testingsimulator.user.data.network.features.tasks.request.UserTaskAnswerRequest
import com.flaringapp.testingsimulator.user.data.network.features.tasks.response.UserTaskResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserTasksApi {

    @POST("Task/answer")
    suspend fun answerTask(
        @Body request: UserTaskAnswerRequest
    ): ApiResponse<UserTaskResponse?>

}