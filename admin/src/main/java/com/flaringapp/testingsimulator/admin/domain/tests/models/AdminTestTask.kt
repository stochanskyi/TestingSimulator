package com.flaringapp.testingsimulator.admin.domain.tests.models

import com.flaringapp.testingsimulator.domain.features.task.Task

class AdminTestTask(
    override val id: Int,
    override val name: String,
    val difficultyLevel: Int,
) : Task