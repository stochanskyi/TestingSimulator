package com.flaringapp.testingsimulator.admin.presentation.test_details

import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestStatusTransformer
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider

class AdminTestStatusActionTransformer(
    private val textProvider: TextProvider,
): AdminTestStatusTransformer<String?> {

    override fun transformDraftStatus(): String {
        return textProvider.getString(R.string.button_admin_task_ready_to_publish)
    }

    override fun transformReadyToPublishStatus(): String {
        return textProvider.getString(R.string.button_admin_task_publish)
    }

    override fun transformPublishedStatus(): String? = null
}