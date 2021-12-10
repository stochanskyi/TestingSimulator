package com.flaringapp.testingsimulator.admin.presentation.task_edit

import com.flaringapp.testingsimulator.admin.domain.tasks.CreateTaskUseCase
import com.flaringapp.testingsimulator.admin.domain.tasks.EditTaskUseCase
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskBlock
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskDetailed
import com.flaringapp.testingsimulator.core.app.common.clearAndAdd
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel
import kotlinx.coroutines.Job
import java.lang.RuntimeException

abstract class AdminTaskEditViewModel : BaseViewModel() {

    abstract fun init(testId: Int, taskId: Int?)

    abstract fun setBlockText(id: Int, text: String)

    abstract fun setBlockEnabled(id: Int, active: Boolean)

    abstract fun changeBlockPosition(id: Int, oldPosition: Int, newPosition: Int)

    abstract fun linkBlock(id: Int)

    abstract fun removeBlock(id: Int)

    abstract fun saveChanges()

}

class AdminTaskEditViewModelImpl(
    private val createTaskUseCase: CreateTaskUseCase,
    private val editTaskUseCase: EditTaskUseCase
) : AdminTaskEditViewModel() {

    private var testId: Int? = null
    private var taskId: Int? = null

    private var currentTask: AdminTaskDetailed? = null

    private val orderedBlocks: MutableList<AdminTaskBlock> = mutableListOf()
    private val disabledBlocks: MutableSet<Int> = hashSetOf()
    private val linkedBlock: MutableMap<Int, Int> = mutableMapOf()

    private var proceedJob: Job? = null

    override fun init(testId: Int, taskId: Int?) {
        this.testId = testId
        this.taskId = taskId

        if (taskId != null) {
            loadTask(taskId)
        }
    }

    override fun setBlockText(id: Int, text: String) {
        // TODO impl
    }

    override fun setBlockEnabled(id: Int, active: Boolean) {
        // TODO impl
    }

    override fun changeBlockPosition(id: Int, oldPosition: Int, newPosition: Int) {
        // TODO impl
    }

    override fun linkBlock(id: Int) {
        // TODO impl
    }

    override fun removeBlock(id: Int) {
        // TODO impl
    }

    override fun saveChanges() {
        // TODO impl
    }

    private fun loadTask(taskId: Int) {
        //TODO load task
        val task = taskMockFunc()

        currentTask = task
        orderedBlocks.clearAndAdd(task.blocks)
        disabledBlocks.clearAndAdd(getDisabledBlocks(task))
    }

    private fun getDisabledBlocks(task: AdminTaskDetailed): Set<Int> {
        return task.blocks
            .asSequence()
            .filter { !it.isEnabled }
            .map { it.id }
            .toSet()
    }

    private fun taskMockFunc(): AdminTaskDetailed {
        throw RuntimeException()
    }

}