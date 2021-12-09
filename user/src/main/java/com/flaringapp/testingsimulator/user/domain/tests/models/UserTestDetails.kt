package com.flaringapp.testingsimulator.user.domain.tests.models

class UserTestDetails(
    override val id: Int,
    override val name: String,
    override val tasksCount: Int,
    override val mark: Int?,
    override val isInProgress: Boolean,
    val tasksPassed: Int,
    val statistics: Map<String, String>
) : UserTest