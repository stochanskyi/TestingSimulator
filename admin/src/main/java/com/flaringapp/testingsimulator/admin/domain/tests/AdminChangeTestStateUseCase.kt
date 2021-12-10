package com.flaringapp.testingsimulator.admin.domain.tests

import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestDetailed
import com.flaringapp.testingsimulator.core.data.common.call.CallResult

interface AdminChangeTestStateUseCase {

    suspend operator fun invoke(testId: Int): CallResult<AdminTestDetailed>

}