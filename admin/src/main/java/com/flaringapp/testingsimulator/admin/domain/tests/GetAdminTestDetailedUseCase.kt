package com.flaringapp.testingsimulator.admin.domain.tests

import com.flaringapp.testingsimulator.admin.data.repository.tests.AdminTestsRepository
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestDetailed
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestTask
import com.flaringapp.testingsimulator.admin.domain.tests.models.status.DraftAdminTestStatus
import com.flaringapp.testingsimulator.core.data.common.call.CallResult

class GetAdminTestDetailedUseCase(
    private val testsRepository: AdminTestsRepository
) {

    suspend operator fun invoke(testId: Int): CallResult<AdminTestDetailed> {

        // TODO mock
//        return testsRepository.getTestDetailed(testId)

        return CallResult.Success(
            AdminTestDetailed(
                id = testId,
                name = "Test $testId",
                tasksCount = 4,
                status = DraftAdminTestStatus(),
                statistics = mapOf(),
                tasks = listOf(
                    AdminTestTask(1, "Task 1", 1),
                    AdminTestTask(2, "Task 2", 2),
                    AdminTestTask(3, "Task 3", 1),
                    AdminTestTask(4, "Task 4", 3),
                ),
            ),
        )
    }
}