package com.flaringapp.testingsimulator.admin.domain.tasks.models

class AdminTaskEdition(
    val taskId: Int,
    val taskName: String,
    val blocks: List<AdminBlockCreation>
)