package com.flaringapp.testingsimulator.domain.features.tests

import com.flaringapp.testingsimulator.core.data.common.call.CallResultList

interface GetTestsUseCase<T : Test> {

    suspend operator fun invoke(topicId: Int): CallResultList<T>

}