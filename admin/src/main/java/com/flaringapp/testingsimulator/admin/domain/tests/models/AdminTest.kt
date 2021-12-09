package com.flaringapp.testingsimulator.admin.domain.tests.models

import com.flaringapp.testingsimulator.domain.features.tests.Test

interface AdminTest : Test {
    val status: AdminTestStatus
}