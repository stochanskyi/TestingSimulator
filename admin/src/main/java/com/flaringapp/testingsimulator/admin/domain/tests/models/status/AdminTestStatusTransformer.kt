package com.flaringapp.testingsimulator.admin.domain.tests.models.status

interface AdminTestStatusTransformer<T> {

    fun transformDraftStatus(): T

    fun transformReadyToPublishStatus(): T

    fun transformPublishedStatus(): T
}