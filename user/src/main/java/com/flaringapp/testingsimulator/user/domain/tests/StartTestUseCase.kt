package com.flaringapp.testingsimulator.user.domain.tests

import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.user.data.repository.tests.UserTestsRepository

class StartTestUseCase(
    private val testsRepository: UserTestsRepository
) {

    suspend operator fun invoke(testId: Int): CallResultNothing {
        return testsRepository.startTest(testId)
    }

}