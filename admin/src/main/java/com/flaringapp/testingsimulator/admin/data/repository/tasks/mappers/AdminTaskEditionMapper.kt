package com.flaringapp.testingsimulator.admin.data.repository.tasks.mappers

import com.flaringapp.testingsimulator.admin.data.network.features.tasks.request.EditAdminTaskRequest
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskEdition

interface AdminTaskEditionMapper {
    fun mapToRequest(task: AdminTaskEdition): EditAdminTaskRequest
}

class AdminTaskEditionMapperImpl(
    private val blockMapper: AdminAddBlockMapper
) : AdminTaskEditionMapper {

    override fun mapToRequest(task: AdminTaskEdition): EditAdminTaskRequest {
        return EditAdminTaskRequest(
            taskId = task.taskId,
            name = task.taskName,
            blocks = blockMapper.mapToRequest(task.blocks)
        )
    }


}