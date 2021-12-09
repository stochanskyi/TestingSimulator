package com.flaringapp.testingsimulator.admin.domain.tests.models

class SimpleAdminTest(
    override val id: Int,
    override val name: String,
    override val tasksCount: Int,
    override val status: AdminTestStatus,
) : AdminTest