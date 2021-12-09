package com.flaringapp.testingsimulator.admin.domain.tests.models

import com.flaringapp.testingsimulator.admin.domain.tests.models.status.AdminTestStatus
import com.flaringapp.testingsimulator.domain.features.tests.Test

class AdminTest(
    override val id: Int,
    override val name: String,
    override val tasksCount: Int,
    val status: AdminTestStatus
) : Test