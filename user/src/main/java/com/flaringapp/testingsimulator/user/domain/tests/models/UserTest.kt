package com.flaringapp.testingsimulator.user.domain.tests.models

import com.flaringapp.testingsimulator.domain.features.tests.Test

private const val MIN_PASSED_MARK = 75

interface UserTest : Test {
    val mark: Int?
    val isInProgress: Boolean

    val isPassed: Boolean
        get() = mark?.let { it > MIN_PASSED_MARK } ?: false
}