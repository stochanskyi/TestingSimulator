package com.flaringapp.testingsimulator.admin.presentation.test_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.admin.domain.tests.GetAdminTestDetailedUseCase
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestDetailed
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.*
import com.flaringapp.testingsimulator.admin.presentation.tests.AdminTestStatusIsEditTaskEnabledTransformer
import com.flaringapp.testingsimulator.admin.presentation.tests.AdminTestStatusIsEditableTransformer
import com.flaringapp.testingsimulator.admin.presentation.tests.AdminTestStatusNameTransformer
import com.flaringapp.testingsimulator.core.app.common.tryAdd
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.LiveDataList
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.MutableLiveDataList
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent
import com.flaringapp.testingsimulator.core.presentation.utils.startLoadingTask
import com.flaringapp.testingsimulator.domain.features.taxonomy.TaxonomyFormatter
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel

abstract class AdminTestDetailsViewModel : BaseViewModel() {

    abstract val nameLiveData: LiveData<String>

    abstract val loadingLiveData: LiveData<Boolean>

    abstract val listItemsLiveData: LiveDataList<AdminTestDetailsItemViewData>

    abstract val openViewTaskLiveData: LiveData<AdminTestDetailsOpenViewTaskViewData>

    abstract val openEditTaskLiveData: LiveData<AdminTestDetailsOpenEditTaskViewData>

    abstract fun init(
        testId: Int,
        testName: String,
    )

    abstract fun openTask(id: Int)

    abstract fun createTask()

}

class AdminTestDetailsViewModeImpl(
    private val getTestDetailedUseCase: GetAdminTestDetailedUseCase,
    private val testStatusNameTransformer: AdminTestStatusNameTransformer,
    private val testStatusIsEditableTransformer: AdminTestStatusIsEditableTransformer,
    private val testStatusIsEditTaskEnabledTransformer: AdminTestStatusIsEditTaskEnabledTransformer,
    private val textProvider: TextProvider,
    private val taxonomyFormatter: TaxonomyFormatter,
) : AdminTestDetailsViewModel() {

    override val nameLiveData = MutableLiveData<String>()

    override val loadingLiveData = MutableLiveData(false)

    override val listItemsLiveData = MutableLiveDataList<AdminTestDetailsItemViewData>()

    override val openViewTaskLiveData = SingleLiveEvent<AdminTestDetailsOpenViewTaskViewData>()

    override val openEditTaskLiveData = SingleLiveEvent<AdminTestDetailsOpenEditTaskViewData>()

    private var test: AdminTestDetailed? = null

    override fun init(testId: Int, testName: String) {
        nameLiveData.value = testName
        loadTest(testId)
    }

    override fun openTask(id: Int) {
        val test = test ?: return
        val task = test.tasks.find { it.id == id } ?: return

        val isEditEnabled = test.status.transform(testStatusIsEditTaskEnabledTransformer)

        if (isEditEnabled) {
            openEditTaskLiveData.value = AdminTestDetailsOpenEditTaskViewData(
                testId = test.id,
                taskId = task.id,
            )
        } else {
            openViewTaskLiveData.value = AdminTestDetailsOpenViewTaskViewData(
                taskId = task.id,
                taskName = task.name,
            )
        }
    }

    override fun createTask() {
        val test = test ?: return

        openEditTaskLiveData.value = AdminTestDetailsOpenEditTaskViewData(
            testId = test.id,
        )
    }

    private fun loadTest(id: Int) {
        viewModelScope.startLoadingTask(loadingLiveData) {
            val loadedTest = safeCall {
                getTestDetailedUseCase(id)
            } ?: return@startLoadingTask

            test = loadedTest

            val items = composeListItemsViewData(loadedTest)

            withMainContext {
                listItemsLiveData.value = items
            }
        }
    }

    private fun composeListItemsViewData(
        test: AdminTestDetailed
    ): List<AdminTestDetailsItemViewData> {
        val items = mutableListOf<AdminTestDetailsItemViewData>()
        items.add(
            composeHeaderViewData(test)
        )
        items.addAll(
            composeTasksViewData(test)
        )
        items.tryAdd(
            composeAddTaskViewData(test)
        )
        return items
    }

    private fun composeHeaderViewData(test: AdminTestDetailed): AdminTestDetailsHeaderViewData {
        val statusNameWithStatistics = LinkedHashMap<CharSequence, CharSequence>(
            test.statistics.size + 1
        )
        val statusLabel = textProvider.getText(R.string.test_status)
        val statusName = test.status.transform(testStatusNameTransformer)

        statusNameWithStatistics[statusLabel] = statusName
        statusNameWithStatistics.putAll(test.statistics)

        return AdminTestDetailsHeaderViewData(
            name = test.name,
            statusAndStatistics = taxonomyFormatter.format(statusNameWithStatistics)
        )
    }

    private fun composeTasksViewData(test: AdminTestDetailed): List<AdminTestDetailsTaskViewData> {
        return test.tasks.map { task ->
            val difficulty = "I".repeat(task.difficultyLevel)
            AdminTestDetailsTaskViewData(
                id = task.id,
                text = taxonomyFormatter.format(task.name, difficulty),
            )
        }
    }

    private fun composeAddTaskViewData(test: AdminTestDetailed): AdminTestDetailsAddTaskViewData? {
        val isEditable = test.status.transform(testStatusIsEditableTransformer)
        if (!isEditable) return null
        return AdminTestDetailsAddTaskViewData
    }

}