package com.flaringapp.testingsimulator.admin.domain.tests.models.status

interface AdminTestStatus {
    fun <T> transform(transform: AdminTestStatusTransformer<T>): T

    fun previousStatus(): AdminTestStatus?
    fun nextStatus(): AdminTestStatus?

}

class DraftAdminTestStatus: AdminTestStatus {

    override fun <T> transform(transform: AdminTestStatusTransformer<T>): T {
        return transform.transformDraftStatus()
    }

    override fun previousStatus(): AdminTestStatus? = null

    override fun nextStatus(): AdminTestStatus = ReadyToPublishAdminTestStatus()

}

class ReadyToPublishAdminTestStatus: AdminTestStatus {

    override fun <T> transform(transform: AdminTestStatusTransformer<T>): T {
        return transform.transformReadyToPublishStatus()
    }

    override fun previousStatus(): AdminTestStatus = DraftAdminTestStatus()

    override fun nextStatus(): AdminTestStatus = PublishedAdminTestStatus()

}


class PublishedAdminTestStatus: AdminTestStatus {

    override fun <T> transform(transform: AdminTestStatusTransformer<T>): T {
        return transform.transformPublishedStatus()
    }

    override fun previousStatus(): AdminTestStatus = ReadyToPublishAdminTestStatus()

    override fun nextStatus(): AdminTestStatus? = null

}


