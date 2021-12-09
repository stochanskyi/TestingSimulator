package com.flaringapp.testingsimulator.admin.domain.tests.models

interface AdminTestStatusTransformer<T> {

    fun transformDraftStatus(): T

    fun transformReadyToPublishStatus(): T

    fun transformPublishedStatus(): T
}