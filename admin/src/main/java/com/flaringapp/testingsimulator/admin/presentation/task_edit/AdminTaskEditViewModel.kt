package com.flaringapp.testingsimulator.admin.presentation.task_edit

import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskBlock
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestTask
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel
import kotlinx.coroutines.Job

abstract class AdminTaskEditViewModel : BaseViewModel() {

    abstract fun setBlockText(id: Int, text: String)

    abstract fun setBlockEnabled(id: Int, active: Boolean)

    abstract fun changeBlockPosition(id: Int, oldPosition: Int, newPosition: Int)

    abstract fun linkBlock(id: Int)

    abstract fun removeBlock(id: Int)

    abstract fun saveChanges()

}

class AdminTaskEditViewModelImpl : AdminTaskEditViewModel() {

    private var testId: Int? = null
    private var taskId: Int? = null

    private var currentTask: AdminTestTask? = null

    private val orderedBlocks: MutableList<AdminTaskBlock> = mutableListOf()
    private val disabledBlocks: MutableSet<Int> = hashSetOf()
    private val linkedBlock: MutableMap<Int, Int> = mutableMapOf()

    private var proceedJob: Job? = null

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
}