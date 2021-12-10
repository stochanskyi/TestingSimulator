package com.flaringapp.testingsimulator.admin.data.repository.tests

import com.flaringapp.testingsimulator.admin.data.network.features.tests.response.AdminTaskPreviewResponse
import com.flaringapp.testingsimulator.admin.data.network.features.tests.response.AdminTestResponse
import com.flaringapp.testingsimulator.admin.data.network.features.tests.response.AdminTestWithStatisticsModel
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTest
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestDetailed
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestTask
import com.flaringapp.testingsimulator.admin.domain.tests.models.SimpleAdminTest
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestStatus
import com.flaringapp.testingsimulator.admin.domain.tests.models.DraftAdminTestStatus
import com.flaringapp.testingsimulator.admin.domain.tests.models.PublishedAdminTestStatus
import com.flaringapp.testingsimulator.admin.domain.tests.models.ReadyToPublishAdminTestStatus


interface AdminTestMapper {
    fun mapTests(dto: List<AdminTestResponse>): List<AdminTest>
    fun mapTest(dto: AdminTestResponse): AdminTest
    fun mapTestWithStatistics(dto: AdminTestWithStatisticsModel): AdminTestDetailed
}

class AdminTestMapperImpl : AdminTestMapper {
    override fun mapTests(dto: List<AdminTestResponse>): List<AdminTest> {
        return dto.map { mapTest(it) }
    }

    override fun mapTest(dto: AdminTestResponse): AdminTest {
        return SimpleAdminTest(
            id = dto.id,
            name = dto.name,
            tasksCount = dto.tasksCount,
            status = mapStatus(dto.status)
        )
    }

    override fun mapTestWithStatistics(dto: AdminTestWithStatisticsModel): AdminTestDetailed {
        return AdminTestDetailed(
            id = dto.test.id,
            name = dto.test.name,
            tasksCount = dto.test.tasksCount,
            status = mapStatus(dto.test.status),
            statistics = dto.statistics,
            tasks = mapTasks(dto.test.tasks),
        )
    }

    private fun mapStatus(status: Int): AdminTestStatus {
        return when (status) {
            0 -> DraftAdminTestStatus()
            1 -> ReadyToPublishAdminTestStatus()
            2 -> PublishedAdminTestStatus()
            else -> throw IllegalStateException("Test status $status not supported by admin")
        }
    }

    private fun mapTasks(tasks: List<AdminTaskPreviewResponse>): List<AdminTestTask> {
        return tasks.map { mapTask(it) }
    }

    private fun mapTask(task: AdminTaskPreviewResponse): AdminTestTask {
        return AdminTestTask(
            id = task.id,
            name = task.text,
            difficultyLevel = task.difficultyLevel,
        )
    }
}