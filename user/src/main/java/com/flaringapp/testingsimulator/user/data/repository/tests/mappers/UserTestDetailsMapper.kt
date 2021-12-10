package com.flaringapp.testingsimulator.user.data.repository.tests.mappers

import com.flaringapp.testingsimulator.user.data.network.features.tests.response.UserTestDetailsResponse
import com.flaringapp.testingsimulator.user.domain.tests.models.UserTestDetails

interface UserTestDetailsMapper {

    fun mapTestDetails(dto: UserTestDetailsResponse): UserTestDetails

}
class UserTestDetailsMapperImpl : UserTestDetailsMapper {

    override fun mapTestDetails(dto: UserTestDetailsResponse): UserTestDetails {
        return UserTestDetails(
            id = dto.test.id,
            name = dto.test.name,
            tasksCount = dto.test.tasksCount,
            mark = dto.test.bestScore,
            isInProgress = dto.test.isInProgress,
            tasksPassed = dto.test.tasksPassed,
            statistics = dto.statistics
        )
    }
}