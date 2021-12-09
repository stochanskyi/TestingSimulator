package com.flaringapp.testingsimulator.user.domain.tests.models

private const val MIN_PASSED_MARK = 75

class SimpleUserTest(
    override val id: Int,
    override val name: String,
    override val tasksCount: Int,
    override val mark: Int?,
    override val isInProgress: Boolean
) : UserTest