package com.flaringapp.testingsimulator.admin.presentation.tests

import com.flaringapp.testingsimulator.admin.domain.tests.AdminCreateTestUseCase
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTest
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.core.data.common.call.transformList
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import com.flaringapp.testingsimulator.domain.features.tests.GetTestsUseCase
import com.flaringapp.testingsimulator.domain.features.tests.Test
import com.flaringapp.testingsimulator.presentation.R as PresentationR
import com.flaringapp.testingsimulator.presentation.features.tests.behaviour.TestsBehaviour
import com.flaringapp.testingsimulator.presentation.features.tests.models.TestViewData

class AdminTestsBehaviour(
    private val getTestsUseCase: GetTestsUseCase<AdminTest>,
    private val createTestUseCase: AdminCreateTestUseCase,
    private val textProvider: TextProvider,
    private val testStatusNameTransformer: AdminTestStatusNameTransformer,
    private val testStatusColorTransformer: AdminTestStatusColorTransformer,
) : TestsBehaviour {

    private var tests: MutableList<AdminTest> = mutableListOf()

    override suspend fun getTests(topicId: Int): CallResultList<TestViewData> {
        return getTestsUseCase(topicId)
            .doOnSuccess { tests = it.toMutableList() }
            .transformList { this.toViewData() }
    }

    override suspend fun createTest(topicId: Int): CallResult<Test> {
        return createTestUseCase(topicId)
            .doOnSuccessSuspend { test ->
                withMainContext { tests.add(test) }
            }
            .transform { this }
    }

    override fun getTest(testId: Int): Test? {
        return tests.firstOrNull { it.id == testId }
    }

    private fun AdminTest.toViewData(): TestViewData {
        val statusName = status.transform(testStatusNameTransformer)
        val statusColor = status.transform(testStatusColorTransformer)

        return TestViewData(
            id = id,
            name = name,
            description = textProvider.getText(PresentationR.string.description_test, tasksCount),
            status = statusName,
            statusColor = statusColor,
        )
    }
}