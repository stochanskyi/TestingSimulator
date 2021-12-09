package com.flaringapp.testingsimulator.user.domain.tasks

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.user.data.repository.tasks.UserTasksRepository
import com.flaringapp.testingsimulator.user.domain.tasks.model.UserTask
import com.flaringapp.testingsimulator.user.domain.tasks.model.UserTaskBlock

class GetUserTaskUseCase(
    private val tasksRepository: UserTasksRepository
) {

    suspend operator fun invoke(testId: Int): CallResult<UserTask> {
//        return tasksRepository.getTask(testId)
        
        return CallResult.Success(
            UserTask(
                id = testId,
                name = "Task $testId",
                orderNumber = 1,
                blocks = listOf(
                    UserTaskBlock(
                        id = 1,
                        text = "Block 1"
                    ),
                    UserTaskBlock(
                        id = 2,
                        text = "Block 2"
                    ),
                    UserTaskBlock(
                        id = 3,
                        text = "Block 3"
                    ),
                    UserTaskBlock(
                        id = 4,
                        text = "Block 4"
                    ),
                    UserTaskBlock(
                        id = 5,
                        text = "Block 5 Block 5 Block 5 Block 5 Block 5 Block 5 Block 5 Block 5"
                    )
                )
            )
        )
    }

}