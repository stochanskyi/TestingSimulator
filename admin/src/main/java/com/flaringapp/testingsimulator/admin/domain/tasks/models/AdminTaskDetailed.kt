package com.flaringapp.testingsimulator.admin.domain.tasks.models

import com.flaringapp.testingsimulator.domain.features.task.Task

class AdminTaskDetailed(
    override val id: Int,
    override val name: String,
    val difficultyLevel: Int,
    val blocks: List<AdminTaskBlock>
): Task