package com.flaringapp.testingsimulator.user.data.repository.tests

import com.flaringapp.testingsimulator.user.data.network.features.tests.response.UserTestResponse
import com.flaringapp.testingsimulator.user.domain.tests.UserTest

interface UserTestMapper {
    fun mapTests(dto: List<UserTestResponse>): List<UserTest>
    fun mapTest(dto: UserTestResponse): UserTest
}

class UserTestMapperImpl : UserTestMapper {
    override fun mapTests(dto: List<UserTestResponse>): List<UserTest> {
        return dto.map { mapTest(it) }
    }

    override fun mapTest(dto: UserTestResponse): UserTest {
        return UserTest(
            id = dto.id,
            name = dto.name,
            tasksCount = dto.tasksCount,
            mark = dto.bestScore,
            isInProgress = dto.isInProgress
        )
    }

}