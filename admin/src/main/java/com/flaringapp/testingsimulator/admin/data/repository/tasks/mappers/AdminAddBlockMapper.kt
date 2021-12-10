package com.flaringapp.testingsimulator.admin.data.repository.tasks.mappers

import com.flaringapp.testingsimulator.admin.data.network.features.tasks.request.AddBlockRequest
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminBlockCreation

interface AdminAddBlockMapper {
    fun mapToRequest(block: AdminBlockCreation): AddBlockRequest
    fun mapToRequest(blocks: List<AdminBlockCreation>): List<AddBlockRequest>

}

class AdminAddBlockMapperImpl : AdminAddBlockMapper {

    override fun mapToRequest(block: AdminBlockCreation): AddBlockRequest {
        return AddBlockRequest(
            text = block.name,
            isEnabled = block.isEnabled,
            linkedBlockPosition = block.linkedBlockId
        )
    }

    override fun mapToRequest(blocks: List<AdminBlockCreation>): List<AddBlockRequest> {
        return blocks.map { mapToRequest(it) }
    }

}