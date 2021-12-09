package com.flaringapp.testingsimulator.admin.domain.tests.models

import com.flaringapp.testingsimulator.admin.domain.tests.models.status.AdminTestStatus

class SimpleAdminTest(
    override val id: Int,
    override val name: String,
    override val tasksCount: Int,
    override val status: AdminTestStatus,
) : AdminTest