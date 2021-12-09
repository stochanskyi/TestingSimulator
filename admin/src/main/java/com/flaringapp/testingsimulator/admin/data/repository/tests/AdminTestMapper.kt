package com.flaringapp.testingsimulator.admin.data.repository.tests

import com.flaringapp.testingsimulator.admin.data.network.features.tests.response.AdminTestResponse
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTest
import com.flaringapp.testingsimulator.admin.domain.tests.models.status.AdminTestStatus
import com.flaringapp.testingsimulator.admin.domain.tests.models.status.DraftAdminTestStatus
import com.flaringapp.testingsimulator.admin.domain.tests.models.status.PublishedAdminTestStatus
import com.flaringapp.testingsimulator.admin.domain.tests.models.status.ReadyToPublishAdminTestStatus


interface AdminTestMapper {
    fun mapTests(dto: List<AdminTestResponse>): List<AdminTest>
    fun mapTest(dto: AdminTestResponse): AdminTest
}

class AdminTestMapperImpl : AdminTestMapper {
    override fun mapTests(dto: List<AdminTestResponse>): List<AdminTest> {
        return dto.map { mapTest(it) }
    }

    override fun mapTest(dto: AdminTestResponse): AdminTest {
        return AdminTest(
            id = dto.id,
            name = dto.name,
            tasksCount = dto.tasksCount,
            status = mapStatus(dto.status)
        )
    }

    private fun mapStatus(status: Int): AdminTestStatus {
        return when (status) {
            1 -> DraftAdminTestStatus()
            2 -> ReadyToPublishAdminTestStatus()
            3 -> PublishedAdminTestStatus()
            else -> throw IllegalStateException("Test status $status not supported by admin")
        }
    }

}