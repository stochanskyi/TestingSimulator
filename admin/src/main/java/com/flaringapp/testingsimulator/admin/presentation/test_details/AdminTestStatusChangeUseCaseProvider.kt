package com.flaringapp.testingsimulator.admin.presentation.test_details

import com.flaringapp.testingsimulator.admin.domain.tests.AdminChangeTestStateUseCase
import com.flaringapp.testingsimulator.admin.domain.tests.AdminPublishTestUseCase
import com.flaringapp.testingsimulator.admin.domain.tests.AdminReadyToPublishTestUseCase
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestStatusTransformer

class AdminTestStatusChangeUseCaseProvider(
    private val readyToPublishUseCase: AdminReadyToPublishTestUseCase,
    private val publishUseCase: AdminPublishTestUseCase,
) : AdminTestStatusTransformer<AdminChangeTestStateUseCase?> {

    override fun transformDraftStatus(): AdminChangeTestStateUseCase {
        return readyToPublishUseCase
    }

    override fun transformReadyToPublishStatus(): AdminChangeTestStateUseCase {
        return publishUseCase
    }

    override fun transformPublishedStatus(): AdminChangeTestStateUseCase? = null
}