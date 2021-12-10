package com.flaringapp.testingsimulator.user.data.network.features.tasks

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.network.base.validateNullable
import com.flaringapp.testingsimulator.user.data.network.features.tasks.request.UserTaskAnswerRequest
import com.flaringapp.testingsimulator.user.data.network.features.tasks.response.UserTaskResponse

interface UserTasksDataSource {

    suspend fun answerTask(request: UserTaskAnswerRequest): CallResult<UserTaskResponse?>

}

class UserTasksDataSourceImpl(
    private val api: UserTasksApi
) : UserTasksDataSource {

    override suspend fun answerTask(request: UserTaskAnswerRequest): CallResult<UserTaskResponse?> {
        return api.answerTask(request).validateNullable()
    }

}