package com.flaringapp.testingsimulator.user.data.repository.tasks

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.user.data.network.features.tasks.UserTasksDataSource
import com.flaringapp.testingsimulator.user.data.network.features.tasks.request.UserTaskAnswerRequest
import com.flaringapp.testingsimulator.user.data.repository.tasks.mapper.PotentialUserTaskMapper
import com.flaringapp.testingsimulator.user.data.repository.tasks.storage.UserTasksStorage
import com.flaringapp.testingsimulator.user.domain.tasks.model.PotentialUserTask
import com.flaringapp.testingsimulator.user.domain.tasks.model.UserTask

interface UserTasksRepository {

    suspend fun getTask(taskId: Int): CallResult<UserTask>

    suspend fun saveTask(task: UserTask)

    suspend fun answerTask(taskId: Int, answerBlockIds: List<Int>): CallResult<PotentialUserTask>

}

class UserTasksRepositoryImpl(
    private val userTasksDataSource: UserTasksDataSource,
    private val userTasksStorage: UserTasksStorage,
    private val potentialUserTaskMapper: PotentialUserTaskMapper
) : UserTasksRepository {

    override suspend fun getTask(taskId: Int): CallResult<UserTask> {
        val task = userTasksStorage[taskId]
            ?: return CallResult.Error("Task with id $taskId not found")
        return CallResult.Success(task)
    }

    override suspend fun saveTask(task: UserTask) {
        userTasksStorage.put(task)
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