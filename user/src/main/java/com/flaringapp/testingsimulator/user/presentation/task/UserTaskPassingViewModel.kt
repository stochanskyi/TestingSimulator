package com.flaringapp.testingsimulator.user.presentation.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import com.flaringapp.testingsimulator.core.presentation.utils.isRunning
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent
import com.flaringapp.testingsimulator.core.presentation.utils.startLoadingTask
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel
import com.flaringapp.testingsimulator.user.R
import com.flaringapp.testingsimulator.user.domain.tasks.AnswerUserTaskUseCase
import com.flaringapp.testingsimulator.user.domain.tasks.GetTaskOrContinueTestUseCase
import com.flaringapp.testingsimulator.user.domain.tasks.model.PotentialUserTask
import com.flaringapp.testingsimulator.user.domain.tasks.model.UserTask
import com.flaringapp.testingsimulator.user.domain.tasks.model.UserTaskBlock
import com.flaringapp.testingsimulator.user.presentation.task.models.UserTaskPassingBlockViewData
import kotlinx.coroutines.Job

abstract class UserTaskPassingViewModel : BaseViewModel() {

    abstract val loadingLiveData: LiveData<Boolean>

    abstract val taskNameLiveData: LiveData<String>
    abstract val taskNumberLiveData: LiveData<String>

    abstract val proceedLiveData: LiveData<String?>

    abstract val blocksLiveData: LiveData<MutableList<UserTaskPassingBlockViewData>>

    abstract val openTestResultLiveData: LiveData<Int>

    abstract fun init(
        testId: Int,
        tasksCount: Int,
        taskId: Int?,
    )

    abstract fun setBlockEnabled(blockId: Int, isEnabled: Boolean)

    abstract fun changeBlockPosition(blockId: Int, oldPosition: Int, newPosition: Int)

    abstract fun submitAnswer()

}

class UserTaskPassingViewModelImpl(
    private val getOrContinueTaskUseCase: GetTaskOrContinueTestUseCase,
    private val answerUserTaskUseCase: AnswerUserTaskUseCase,
    private val textProvider: TextProvider
) : UserTaskPassingViewModel() {

    override val loadingLiveData = MutableLiveData(false)

    override val taskNameLiveData = MutableLiveData<String>()

    override val taskNumberLiveData = MutableLiveData<String>()

    override val proceedLiveData = MutableLiveData<String?>(null)

    override val blocksLiveData = MutableLiveData<MutableList<UserTaskPassingBlockViewData>>()
    override val openTestResultLiveData = SingleLiveEvent<Int>()

    private var testId: Int? = null
    private var tasksCount: Int? = null
    private var taskId: Int? = null

    private var currentTask: UserTask? = null

    private val orderedBlocks: MutableList<UserTaskBlock> = mutableListOf()
    private val disabledBlocks: MutableSet<Int> = hashSetOf()

    private var proceedJob: Job? = null

    override fun init(testId: Int, tasksCount: Int, taskId: Int?) {
        this.testId = testId
        this.tasksCount = tasksCount
        this.taskId = taskId

        loadTask()
    }

    override fun setBlockEnabled(blockId: Int, isEnabled: Boolean) {
        if (isEnabled) {
            disabledBlocks.remove(blockId)
        } else {
            disabledBlocks.add(blockId)
        }
    }

    override fun changeBlockPosition(blockId: Int, oldPosition: Int, newPosition: Int) {
        val item = orderedBlocks.removeAt(oldPosition)
        orderedBlocks.add(newPosition, item)
    }

    override fun submitAnswer() {
        if (proceedJob.isRunning) return
        val taskId = currentTask?.id ?: return

        proceedJob = viewModelScope.startLoadingTask(loadingLiveData) {
            val blockIds = orderedBlocks.filter { !disabledBlocks.contains(it.id) }.map { it.id }
            val result = safeCall {
                answerUserTaskUseCase(taskId, blockIds)
            } ?: return@startLoadingTask

            withMainContext {
                processPotentialUserTask(result)
            }
        }
    }

    private fun processPotentialUserTask(potentialTask: PotentialUserTask) {
        if (potentialTask.userTask == null) {
            val currentTestId = testId ?: return
            openTestResultLiveData.value = currentTestId
        } else {
            processNewTask(potentialTask.userTask)
        }
    }

    private fun loadTask() {
        val testId = testId ?: return
        val taskId = taskId

        viewModelScope.startLoadingTask(loadingLiveData) {
            val task = safeCall {
                getOrContinueTaskUseCase(testId, taskId)
            } ?: return@startLoadingTask

            withMainContext {
                processNewTask(task)
            }
        }
    }

    private fun processNewTask(task: UserTask) {
        taskId = task.id
        currentTask = task
        orderedBlocks.addAll(task.blocks)
        updateTaskViewData(task)
    }

    private fun updateTaskViewData(task: UserTask) {
        taskNameLiveData.value = task.name

        taskNumberLiveData.value = textProvider.getText(
            R.string.task_passing_number,
            task.orderNumber,
            tasksCount ?: 0
        ).toString()

        proceedLiveData.value = textProvider.getString(
            if (task.orderNumber == tasksCount) R.string.button_task_passing_finish
            else R.string.button_task_passing_next
        )

        // TODO improve data mapping - move to background thread
        blocksLiveData.value = task.blocks.toViewData()
    }

    private fun List<UserTaskBlock>.toViewData(): MutableList<UserTaskPassingBlockViewData> {
        return mapTo(ArrayList(size)) { block ->
            block.toViewData()
        }
    }

    private fun UserTaskBlock.toViewData(): UserTaskPassingBlockViewData {
        return UserTaskPassingBlockViewData(
            id = id,
            text = text,
            isBlockActive = true,
        )
    }

}