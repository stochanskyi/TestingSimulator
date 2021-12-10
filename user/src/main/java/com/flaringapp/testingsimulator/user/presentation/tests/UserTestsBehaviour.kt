package com.flaringapp.testingsimulator.user.presentation.tests

import com.flaringapp.testingsimulator.core.data.color.ColorProvider
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.core.data.common.call.transformList
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import com.flaringapp.testingsimulator.domain.features.tests.GetTestsUseCase
import com.flaringapp.testingsimulator.domain.features.tests.Test
import com.flaringapp.testingsimulator.presentation.R as PresentationR
import com.flaringapp.testingsimulator.presentation.features.tests.behaviour.TestsBehaviour
import com.flaringapp.testingsimulator.presentation.features.tests.models.TestViewData
import com.flaringapp.testingsimulator.user.R
import com.flaringapp.testingsimulator.user.domain.tests.models.UserTest

class UserTestsBehaviour(
    private val getTestsUseCase: GetTestsUseCase<UserTest>,
    private val textProvider: TextProvider,
    private val colorProvider: ColorProvider,
) : TestsBehaviour {

    private var tests: List<UserTest> = emptyList()

    override suspend fun getTests(topicId: Int): CallResultList<TestViewData> {
        return getTestsUseCase(topicId)
            .doOnSuccess { tests = it }
            .transformList { this.toViewData() }
    }

    override suspend fun createTest(topicId: Int): CallResult<Test> {
        return CallResult.Error("Not available")
    }

    override fun getTest(testId: Int): Test? {
        return tests.firstOrNull { it.id == testId }
    }

    private fun UserTest.toViewData() : TestViewData {
        val status: CharSequence
        val statusColor: Int
        when {
            isInProgress -> {
                status = textProvider.getString(R.string.task_in_progress)
                statusColor = colorProvider.getColor(R.color.task_in_progress)
            }
            mark == null -> {
                status = ""
                statusColor = colorProvider.transparent()
            }
            else -> {
                status = textProvider.formatPercent(mark.toString())
                statusColor = colorProvider.getColor(
                    if (isPassed) R.color.task_passed
                    else R.color.task_failed
                )
            }
        }

        return TestViewData(
            id = id,
            name = name,
            description = textProvider.getText(PresentationR.string.description_test, tasksCount),
            status = status,
            statusColor = statusColor,
        )
    }
}