package com.flaringapp.testingsimulator.admin.domain.tests.models

class AdminTestDetailed(
    override val id: Int,
    override val name: String,
    override val tasksCount: Int,
    override val status: AdminTestStatus,
    val statistics: Map<String, String>,
    val tasks: List<AdminTestTask>,
) : AdminTest