package com.flaringapp.testingsimulator.user.data.repository.tasks.storage

import com.flaringapp.testingsimulator.user.domain.tasks.model.UserTask
import java.util.concurrent.ConcurrentHashMap

interface UserTasksStorage {

    operator fun get(id: Int): UserTask?

    fun put(userTask: UserTask)

}

class UserTasksStorageImpl : UserTasksStorage {

    private val tasks = ConcurrentHashMap<Int, UserTask>()

    override fun get(id: Int): UserTask? {
        return tasks[id]
    }

    override fun put(userTask: UserTask) {
        tasks[userTask.id] = userTask
    }
}