package com.flaringapp.testingsimulator.user.domain.tests

import com.flaringapp.testingsimulator.domain.features.tests.Test

private const val MIN_PASSED_MARK = 75

class UserTest(
    override val id: Int,
    override val name: String,
    override val tasksCount: Int,
    val mark: Int,
    val inProgress: Boolean
) : Test {

    val isPassed = mark > MIN_PASSED_MARK

}