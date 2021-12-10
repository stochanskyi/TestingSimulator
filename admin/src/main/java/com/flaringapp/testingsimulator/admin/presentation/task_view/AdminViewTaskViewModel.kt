package com.flaringapp.testingsimulator.admin.presentation.task_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.admin.domain.tasks.GetAdminTaskUseCase
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskBlock
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskDetailed
import com.flaringapp.testingsimulator.admin.presentation.task_view.models.AdminViewTaskBlockViewData
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.LiveDataList
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.MutableLiveDataList
import com.flaringapp.testingsimulator.core.presentation.utils.startLoadingTask
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel

abstract class AdminViewTaskViewModel : BaseViewModel() {

    abstract val nameLiveData: LiveData<String>

    abstract val loadingLiveData: LiveData<Boolean>

    abstract val blocksLiveData: LiveDataList<AdminViewTaskBlockViewData>

    abstract fun init(taskId: Int, taskName: String)

}

class AdminViewTaskViewModelImpl(
    private val getAdminTaskUseCase: GetAdminTaskUseCase,
) : AdminViewTaskViewModel() {

    override val nameLiveData = MutableLiveData("")

    override val loadingLiveData = MutableLiveData(false)

    override val blocksLiveData = MutableLiveDataList<AdminViewTaskBlockViewData>(emptyList())

    private var taskId: Int? = null
    private var taskName: String? = null

    override fun init(taskId: Int, taskName: String) {
        this.taskId = taskId
        this.taskName = taskName

        nameLiveData.value = taskName

        loadData()
    }

    private fun loadData() {
        val taskId = taskId ?: return
        viewModelScope.startLoadingTask(loadingLiveData) {
            val task = safeCall {
                getAdminTaskUseCase(taskId)
            } ?: return@startLoadingTask

            val viewData = task.toViewData()

            withMainContext {
                blocksLiveData.value = viewData
            }
        }
    }

    private fun AdminTaskDetailed.toViewData(): List<AdminViewTaskBlockViewData> {
        return blocks.map { it.toViewData() }
    }

    private fun AdminTaskBlock.toViewData() : AdminViewTaskBlockViewData {
        return AdminViewTaskBlockViewData(
            text = text,
            isEnabled = !isEnabled,
            isLinked = linkedBlockId != null,
        )
    }
}