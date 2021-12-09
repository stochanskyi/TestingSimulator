package com.flaringapp.testingsimulator.domain.features.tests

import com.flaringapp.testingsimulator.core.data.common.call.CallResult

interface GetTestsUseCase<T : Test> {

    suspend fun getTests(topicId: Int): CallResult<List<T>>

}