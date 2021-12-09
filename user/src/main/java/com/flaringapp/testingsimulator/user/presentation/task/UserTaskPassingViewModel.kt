package com.flaringapp.testingsimulator.user.presentation.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent
import com.flaringapp.testingsimulator.core.presentation.utils.startLoadingTask
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel
import com.flaringapp.testingsimulator.user.R
import com.flaringapp.testingsimulator.user.domain.tasks.AnswerUserTaskUseCase
import com.flaringapp.testingsimulator.user.domain.tasks.GetUserTaskUseCase
import com.flaringapp.testingsimulator.user.domain.tasks.model.PotentialUserTask
import com.flaringapp.testingsimulator.user.domain.tasks.model.UserTask
import com.flaringapp.testingsimulator.user.domain.tasks.model.UserTaskBlock
import com.flaringapp.testingsimulator.user.presentation.task.models.UserTaskPassingBlockViewData

abstract class UserTaskPassingViewModel : BaseViewModel() {

    abstract val loadingLiveData: LiveData<Boolean>

    abstract val taskNameLiveData: LiveData<String>
    abstract val taskNumberLiveData: LiveData<String>

    abstract val proceedLiveData: LiveData<String?>

    abstract val blocksLiveData: LiveData<MutableList<UserTaskPassingBlockViewData>>

    abstract val openTestResultLiveData: LiveData<Int>

    abstract fun init(testId: Int, tasksCount: Int)

    abstract fun setBlockEnabled(blockId: Int, isEnabled: Boolean)

    abstract fun changeBlockPosition(blockId: Int, oldPosition: Int, newPosition: Int)

    abstract fun submitAnswer()

}

class UserTaskPassingViewModelImpl(
    private val getUserTaskUseCase: GetUserTaskUseCase,
    private val answerUserTaskUseCase: AnswerUserTaskUseCase,
    private val textProvider: TextProvider
): UserTaskPassingViewModel() {

    override val loadingLiveData = MutableLiveData(false)

    override val taskNameLiveData = MutableLiveData<String>()

    override val taskNumberLiveData = MutableLiveData<String>()

    override val proceedLiveData = MutableLiveData<String?>(null)

    override val blocksLiveData = MutableLiveData<MutableList<UserTaskPassingBlockViewData>>()
    override val openTestResultLiveData = SingleLiveEvent<Int>()

    private var tasksCount: Int? = null

    private var testId: Int? = null

    override fun init(testId: Int, tasksCount: Int) {
        this.tasksCount = tasksCount
        this.testId = testId

        loadTask(testId)
    }

    private var currentTask: UserTask? = null

    override fun setBlockEnabled(blockId: Int, isEnabled: Boolean) {
        //TODO implement
    }

    override fun changeBlockPosition(blockId: Int, oldPosition: Int, newPosition: Int) {
        //TODO implement
    }

    override fun submitAnswer() {
        val taskId = currentTask?.id ?: return
        viewModelScope.startLoadingTask(loadingLiveData) {
            val result = safeCall { answerUserTaskUseCase(taskId, emptyList()) } ?: return@startLoadingTask

            withMainContext { processPotentialUserTask(result) }
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

    private fun loadTask(testId: Int) {
        viewModelScope.startLoadingTask(loadingLiveData) {
            val task = safeCall { getUserTaskUseCase(testId) } ?: return@startLoadingTask

            withMainContext {
                processNewTask(task)
            }
        }
    }

    private fun processNewTask(task: UserTask) {
        currentTask = task
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