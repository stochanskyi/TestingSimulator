package com.flaringapp.testingsimulator.admin.presentation.tests

import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestStatusTransformer
import com.flaringapp.testingsimulator.core.data.color.ColorProvider

class AdminTestStatusColorTransformer(
    private val colorProvider: ColorProvider
) : AdminTestStatusTransformer<Int> {

    override fun transformDraftStatus(): Int {
        return colorProvider.getColor(R.color.task_draft)
    }

    override fun transformReadyToPublishStatus(): Int {
        return colorProvider.getColor(R.color.task_ready_to_publish)
    }

    override fun transformPublishedStatus(): Int {
        return colorProvider.getColor(R.color.task_published)
    }

}