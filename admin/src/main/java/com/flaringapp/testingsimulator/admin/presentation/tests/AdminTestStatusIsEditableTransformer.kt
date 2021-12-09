package com.flaringapp.testingsimulator.admin.presentation.tests

import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestStatusTransformer

class AdminTestStatusIsEditableTransformer : AdminTestStatusTransformer<Boolean> {

    override fun transformDraftStatus(): Boolean = true

    override fun transformReadyToPublishStatus(): Boolean = false

    override fun transformPublishedStatus(): Boolean = false
}