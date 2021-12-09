package com.flaringapp.testingsimulator.user.data.network.features.tasks

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.network.base.validate
import com.flaringapp.testingsimulator.user.data.network.features.tasks.request.StartTestRequest
import com.flaringapp.testingsimulator.user.data.network.features.tasks.request.TaskAnswerRequest
import com.flaringapp.testingsimulator.user.data.network.features.tasks.response.UserTaskResponse

interface UserTasksDataSource {

    suspend fun startTest(request: StartTestRequest): CallResult<UserTaskResponse>

    suspend fun getTask(testId: Int): CallResult<UserTaskResponse>

    suspend fun answerTask(request: TaskAnswerRequest): CallResult<UserTaskResponse?>

}

class UserTasksDataSourceImpl(
    private val api: UserTasksApi
) : UserTasksDataSource {

    override suspend fun startTest(request: StartTestRequest): CallResult<UserTaskResponse> {
        return api.startTest(request).validate()
    }

    override suspend fun getTask(testId: Int): CallResult<UserTaskResponse> {
        return api.getTask(testId).validate()
    }

    override suspend fun answerTask(request: TaskAnswerRequest): CallResult<UserTaskResponse?> {
        return api.answerTask(request).validate()
    }

}