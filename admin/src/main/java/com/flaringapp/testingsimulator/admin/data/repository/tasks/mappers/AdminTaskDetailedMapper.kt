package com.flaringapp.testingsimulator.admin.data.repository.tasks.mappers

import com.flaringapp.testingsimulator.admin.data.network.features.tasks.response.AdminTaskBlockResponse
import com.flaringapp.testingsimulator.admin.data.network.features.tasks.response.AdminTaskResponse
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskBlock
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskDetailed

interface AdminTaskDetailedMapper {
    fun mapTask(dto: AdminTaskResponse): AdminTaskDetailed

    fun mapBlock(dto: AdminTaskBlockResponse): AdminTaskBlock
    fun mapBlocks(dto: List<AdminTaskBlockResponse>): List<AdminTaskBlock>

}

class AdminTaskDetailedMapperImpl : AdminTaskDetailedMapper {
    override fun mapTask(dto: AdminTaskResponse): AdminTaskDetailed {
        return AdminTaskDetailed(
            id = dto.id,
            name = dto.name,
            difficultyLevel = dto.difficultyLevel,
            blocks = mapBlocks(dto.blocks)
        )
    }

    override fun mapBlock(dto: AdminTaskBlockResponse): AdminTaskBlock {
        return AdminTaskBlock(
            id = dto.id,
            text = dto.text,
            isEnabled = dto.isEnabled,
            linkedBlockId = dto.linkedBlockId
        )
    }

    override fun mapBlocks(dto: List<AdminTaskBlockResponse>): List<AdminTaskBlock> {
        return dto.map { mapBlock(it) }
    }
}