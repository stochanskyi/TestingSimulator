package com.flaringapp.testingsimulator.admin.domain.tests

import com.flaringapp.testingsimulator.admin.data.repository.tests.AdminTestsRepository
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestDetailed
import com.flaringapp.testingsimulator.core.data.common.call.CallResult

class GetAdminTestDetailedUseCase(
    private val testsRepository: AdminTestsRepository
) {

    suspend operator fun invoke(testId: Int): CallResult<AdminTestDetailed> {
        return testsRepository.getTestDetailed(testId)
    }
}