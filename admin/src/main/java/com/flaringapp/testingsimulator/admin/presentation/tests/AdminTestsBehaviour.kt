package com.flaringapp.testingsimulator.admin.presentation.tests

import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTest
import com.flaringapp.testingsimulator.admin.domain.tests.models.status.AdminTestStatusTransformer
import com.flaringapp.testingsimulator.core.data.color.ColorProvider
import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.core.data.common.call.transformList
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import com.flaringapp.testingsimulator.domain.features.tests.GetTestsUseCase
import com.flaringapp.testingsimulator.presentation.R as PresentationR
import com.flaringapp.testingsimulator.presentation.features.tests.behaviour.TestsBehaviour
import com.flaringapp.testingsimulator.presentation.features.tests.models.TestViewData

class AdminTestsBehaviour(
    private val getTestsUseCase: GetTestsUseCase<AdminTest>,
    private val textProvider: TextProvider,
    private val colorProvider: ColorProvider,
) : TestsBehaviour {

    private val testStatusAndColorTransformer = AdminTestStatusAndColorTransformer()

    override suspend fun getTests(moduleId: Int): CallResultList<TestViewData> {
        return getTestsUseCase(moduleId)
            .transformList { this.toViewData() }
    }

    private fun AdminTest.toViewData(): TestViewData {
        val statusAndColor = status.transform(testStatusAndColorTransformer)

        return TestViewData(
            id = id,
            name = name,
            description = textProvider.getText(PresentationR.string.description_test, tasksCount),
            status = statusAndColor.first,
            statusColor = statusAndColor.second,
        )
    }

    private inner class AdminTestStatusAndColorTransformer :
        AdminTestStatusTransformer<Pair<CharSequence, Int>> {

        override fun transformDraftStatus(): Pair<CharSequence, Int> {
            return textProvider.getString(R.string.task_draft) to
                colorProvider.getColor(R.color.task_draft)
        }

        override fun transformReadyToPublishStatus(): Pair<CharSequence, Int> {
            return textProvider.getString(R.string.task_ready_to_publish) to
                colorProvider.getColor(R.color.task_ready_to_publish)
        }

        override fun transformPublishedStatus(): Pair<CharSequence, Int> {
            return textProvider.getString(R.string.task_published) to
                colorProvider.getColor(R.color.task_published)
        }
    }
}