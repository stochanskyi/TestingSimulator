package com.flaringapp.testingsimulator.admin.presentation.task_edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.admin.domain.tasks.CreateTaskUseCase
import com.flaringapp.testingsimulator.admin.domain.tasks.EditTaskUseCase
import com.flaringapp.testingsimulator.admin.domain.tasks.GetAdminTaskUseCase
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminBlockCreation
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskBlock
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskDetailed
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskEdition
import com.flaringapp.testingsimulator.admin.presentation.task_edit.models.AddBlockViewData
import com.flaringapp.testingsimulator.admin.presentation.task_edit.models.AdminTaskEditBlockViewData
import com.flaringapp.testingsimulator.core.app.common.clearAndAdd
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.presentation.utils.isRunning
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent
import com.flaringapp.testingsimulator.core.presentation.utils.startLoadingTask
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel
import kotlinx.coroutines.Job
import java.util.concurrent.atomic.AtomicInteger

abstract class AdminTaskEditViewModel : BaseViewModel() {

    abstract val taskNameLiveData: LiveData<String>

    abstract val loadingLiveData: LiveData<Boolean>

    abstract val blocksLiveData: LiveData<MutableList<AdminTaskEditBlockViewData>>

    abstract val addNewBlockLiveData: LiveData<AddBlockViewData>

    abstract val removeBlockAtPositionLiveData: LiveData<Int>

    abstract val openTestScreen: LiveData<Unit>

    abstract fun init(testId: Int, taskId: Int?)

    abstract fun setName(name: String)

    abstract fun createBlock()

    abstract fun setBlockText(id: Int, text: String)

    abstract fun setBlockEnabled(id: Int, active: Boolean)

    abstract fun changeBlockPosition(id: Int, oldPosition: Int, newPosition: Int)

    abstract fun linkBlock(id: Int)

    abstract fun removeBlock(id: Int)

    abstract fun saveChanges()

}

class AdminTaskEditViewModelImpl(
    private val createTaskUseCase: CreateTaskUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val getAdminTaskUseCase: GetAdminTaskUseCase
) : AdminTaskEditViewModel() {

    private var testId: Int? = null
    private var taskId: Int? = null

    private var name: String = ""

    private var currentTask: AdminTaskDetailed? = null

    private val orderedBlocks: MutableList<AdminTaskBlock> = mutableListOf()
    private val disabledBlocks: MutableSet<Int> = hashSetOf()
    private val linkedBlock: MutableMap<Int, Int> = mutableMapOf()

    private var proceedJob: Job? = null

    override val taskNameLiveData = MutableLiveData<String>()

    override val loadingLiveData = MutableLiveData<Boolean>()

    override val blocksLiveData = MutableLiveData<MutableList<AdminTaskEditBlockViewData>>()

    override val addNewBlockLiveData = SingleLiveEvent<AddBlockViewData>()

    override val removeBlockAtPositionLiveData = SingleLiveEvent<Int>()

    override val openTestScreen = SingleLiveEvent<Unit>()

    private val taskIds = AtomicInteger(0)

    override fun init(testId: Int, taskId: Int?) {
        this.testId = testId
        this.taskId = taskId

        if (taskId != null) {
            loadTask(taskId)
        }
    }

    override fun setName(name: String) {
        this.name = name
        taskNameLiveData.value = name
    }

    override fun createBlock() {
        // TODO improvement calculate block position
        val newBlock = AdminTaskBlock(
            taskIds.incrementAndGet(),
            "",
            false,
            null
        )
        val position = orderedBlocks.size

        orderedBlocks.add(newBlock)
        addNewBlockLiveData.value = AddBlockViewData(position, newBlock.toViewData())
    }

    override fun setBlockText(id: Int, text: String) {
        changeBlock(id) {
            it.copy(text = text)
        }
    }

    override fun setBlockEnabled(id: Int, active: Boolean) {
        changeBlock(id) {
            it.copy(isEnabled = active)
        }
    }

    override fun changeBlockPosition(id: Int, oldPosition: Int, newPosition: Int) {
        val item = orderedBlocks.removeAt(oldPosition)
        orderedBlocks.add(newPosition, item)
    }

    override fun linkBlock(id: Int) {
        // TODO impl
    }

    override fun removeBlock(id: Int) {
        val position = orderedBlocks.indexOfFirst { it.id == id }

        orderedBlocks.removeAt(position)
        removeBlockAtPositionLiveData.value = position
    }

    override fun saveChanges() {
        taskId?.let { editTask(it) } ?: createTask()
    }

    private fun editTask(taskId: Int) {
        if (proceedJob.isRunning) return
        proceedJob = viewModelScope.startLoadingTask(loadingLiveData) {
            val task = AdminTaskEdition(
                taskId,
                name,
                orderedBlocks.map { it.asAdminBlockCreation() }
            )

            safeCall { editTaskUseCase(task) } ?: return@startLoadingTask

            openTestScreen.call()
        }
    }

    private fun createTask() {
        if (proceedJob.isRunning) return
        proceedJob = viewModelScope.startLoadingTask(loadingLiveData) {
            val safeTestId = testId ?: return@startLoadingTask
            safeCall {
                createTaskUseCase(
                    safeTestId,
                    name,
                    orderedBlocks.map { it.asAdminBlockCreation() }
                )
            } ?: return@startLoadingTask

            openTestScreen.call()
        }
    }

    private fun loadTask(taskId: Int) {
        viewModelScope.startLoadingTask(loadingLiveData) {
            val result = safeCall { getAdminTaskUseCase(taskId) } ?: return@startLoadingTask

            withMainContext { initTask(result) }
        }
    }

    private fun initTask(task: AdminTaskDetailed) {
        currentTask = task
        orderedBlocks.clearAndAdd(task.blocks)
        disabledBlocks.clearAndAdd(getDisabledBlocks(task))

        updateTaskViewData(task)
    }

    private fun updateTaskViewData(task: AdminTaskDetailed) {
        taskNameLiveData.value = task.name
        blocksLiveData.value = task.blocks.toViewData()
    }

    private fun getDisabledBlocks(task: AdminTaskDetailed): Set<Int> {
        return task.blocks
            .asSequence()
            .filter { !it.isEnabled }
            .map { it.id }
            .toSet()
    }

    private fun List<AdminTaskBlock>.toViewData(): MutableList<AdminTaskEditBlockViewData> {
        return mapTo(ArrayList()) { it.toViewData() }
    }

    private fun AdminTaskBlock.toViewData(): AdminTaskEditBlockViewData {
        return AdminTaskEditBlockViewData(
            id = id,
            text = text,
            isBlockActive = isEnabled,
            isLinked = linkedBlockId != null
        )
    }

    private inline fun changeBlock(id: Int, action: (AdminTaskBlock) -> AdminTaskBlock) {
        val position = orderedBlocks.indexOfFirst { it.id == id }.takeIf { it >= 0 } ?: return
        val newItem = action(orderedBlocks[position])

        orderedBlocks[position] = newItem
    }

    private fun AdminTaskBlock.asAdminBlockCreation(): AdminBlockCreation {
        return AdminBlockCreation(name, isEnabled, linkedBlockId)
    }

}