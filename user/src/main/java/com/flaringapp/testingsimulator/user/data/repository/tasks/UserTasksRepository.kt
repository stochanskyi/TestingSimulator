package com.flaringapp.testingsimulator.user.data.repository.tasks

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.user.data.network.features.tasks.UserTasksDataSource
import com.flaringapp.testingsimulator.user.data.network.features.tasks.request.StartTestRequest
import com.flaringapp.testingsimulator.user.data.network.features.tasks.request.UserTaskAnswerRequest
import com.flaringapp.testingsimulator.user.data.repository.tasks.mapper.PotentialUserTaskMapper
import com.flaringapp.testingsimulator.user.data.repository.tasks.mapper.UserTaskMapper
import com.flaringapp.testingsimulator.user.data.repository.tasks.model.PotentialUserTask
import com.flaringapp.testingsimulator.user.data.repository.tasks.model.UserTask

interface UserTasksRepository {

    suspend fun getTask(testId: Int): CallResult<UserTask>

    suspend fun answerTask(taskId: Int, answerBlockIds: List<Int>): CallResult<PotentialUserTask>

}

class UserTasksRepositoryImpl(
    private val userTasksDataSource: UserTasksDataSource,
    private val userTaskMapper: UserTaskMapper,
    private val potentialUserTaskMapper: PotentialUserTaskMapper
) : UserTasksRepository {

    override suspend fun getTask(testId: Int): CallResult<UserTask> {
        return userTasksDataSource.getTask(testId).transform { userTaskMapper.mapUserTask(this) }
    }

    override suspend fun answerTask(
        taskId: Int,
        answerBlockIds: List<Int>
    ): CallResult<PotentialUserTask> {
        val request = UserTaskAnswerRequest(taskId, answerBlockIds)

        return userTasksDataSource.answerTask(request).transform {
            potentialUserTaskMapper.mapUserTask(this)
        }

    }

}