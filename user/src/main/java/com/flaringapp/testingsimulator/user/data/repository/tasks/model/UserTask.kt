package com.flaringapp.testingsimulator.user.data.repository.tasks.model

import com.flaringapp.testingsimulator.domain.features.task.Task

class UserTask(
    override val id: Int,
    override val name: String,
    val orderNumber: Int,
    val blocks: List<UserTaskBlock>
) : Task