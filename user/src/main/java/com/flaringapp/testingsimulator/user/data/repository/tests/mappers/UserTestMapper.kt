package com.flaringapp.testingsimulator.user.data.repository.tests.mappers

import com.flaringapp.testingsimulator.user.data.network.features.tests.response.UserTestResponse
import com.flaringapp.testingsimulator.user.domain.tests.models.SimpleUserTest
import com.flaringapp.testingsimulator.user.domain.tests.models.UserTest

interface UserTestMapper {
    fun mapTests(dto: List<UserTestResponse>): List<UserTest>
    fun mapTest(dto: UserTestResponse): UserTest
}

class UserTestMapperImpl : UserTestMapper {
    override fun mapTests(dto: List<UserTestResponse>): List<UserTest> {
        return dto.map { mapTest(it) }
    }

    override fun mapTest(dto: UserTestResponse): UserTest {
        return SimpleUserTest(
            id = dto.id,
            name = dto.name,
            tasksCount = dto.tasksCount,
            mark = dto.bestScore,
            isInProgress = dto.isInProgress
        )
    }
}