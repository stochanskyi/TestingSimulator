package com.flaringapp.testingsimulator.user.data.repository.tasks.mapper

import com.flaringapp.testingsimulator.user.data.network.features.tasks.response.UserTaskResponse
import com.flaringapp.testingsimulator.user.data.repository.tasks.model.PotentialUserTask

interface PotentialUserTaskMapper {
    fun mapUserTask(dto: UserTaskResponse?): PotentialUserTask
}

class PotentialUserTaskMapperImpl(
    private val userTaskMapper: UserTaskMapper
): PotentialUserTaskMapper {

    override fun mapUserTask(dto: UserTaskResponse?): PotentialUserTask {
        val userTask = dto?.let { userTaskMapper.mapUserTask(it) }

        return PotentialUserTask(userTask)
    }

}
