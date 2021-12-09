package com.flaringapp.testingsimulator.user.data.repository.tasks.mapper

import com.flaringapp.testingsimulator.user.data.network.features.tasks.response.UserTaskBlockResponse
import com.flaringapp.testingsimulator.user.data.network.features.tasks.response.UserTaskResponse
import com.flaringapp.testingsimulator.user.domain.tasks.model.UserTask
import com.flaringapp.testingsimulator.user.domain.tasks.model.UserTaskBlock

interface UserTaskMapper {
    fun mapUserTask(dto: UserTaskResponse): UserTask
}

class UserTaskMapperImpl : UserTaskMapper {
    override fun mapUserTask(dto: UserTaskResponse): UserTask {
        return UserTask(
            id = dto.id,
            name = dto.name,
            orderNumber = dto.currentOrderNumber,
            blocks = mapBlocks(dto.blocks)
        )
    }

    private fun mapBlock(dto: UserTaskBlockResponse): UserTaskBlock {
        return UserTaskBlock(
            id = dto.id,
            text = dto.text
        )
    }

    private fun mapBlocks(dto: List<UserTaskBlockResponse>): List<UserTaskBlock> {
        return dto.map { mapBlock(it) }
    }

}