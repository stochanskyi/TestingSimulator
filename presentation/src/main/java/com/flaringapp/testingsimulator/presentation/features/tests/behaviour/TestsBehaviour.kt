package com.flaringapp.testingsimulator.presentation.features.tests.behaviour

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.domain.features.tests.Test
import com.flaringapp.testingsimulator.presentation.features.tests.models.TestViewData

interface TestsBehaviour {

    suspend fun getTests(topicId: Int) : CallResultList<TestViewData>

    suspend fun createTest(topicId: Int) : CallResult<Test>

    fun getTest(testId: Int): Test?

}