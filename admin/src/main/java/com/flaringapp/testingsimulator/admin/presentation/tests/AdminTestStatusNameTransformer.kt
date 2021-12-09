package com.flaringapp.testingsimulator.admin.presentation.tests

import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestStatusTransformer
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider

class AdminTestStatusNameTransformer(
    private val textProvider: TextProvider,
) : AdminTestStatusTransformer<CharSequence> {

    override fun transformDraftStatus(): CharSequence {
        return textProvider.getString(R.string.task_draft)
    }

    override fun transformReadyToPublishStatus(): CharSequence {
        return textProvider.getString(R.string.task_ready_to_publish)
    }

    override fun transformPublishedStatus(): CharSequence {
        return textProvider.getString(R.string.task_published)
    }

}