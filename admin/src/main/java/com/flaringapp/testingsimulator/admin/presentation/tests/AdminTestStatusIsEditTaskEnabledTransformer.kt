package com.flaringapp.testingsimulator.admin.presentation.tests

import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestStatusTransformer

    class AdminTestStatusIsEditTaskEnabledTransformer : AdminTestStatusTransformer<Boolean> {

    override fun transformDraftStatus(): Boolean {
        return true
    }

    override fun transformReadyToPublishStatus(): Boolean {
        return false
    }

    override fun transformPublishedStatus(): Boolean {
        return true

    }
}