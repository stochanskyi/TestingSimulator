package com.flaringapp.testingsimulator.admin.presentation.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.admin.domain.tests.GetAdminTestDetailedUseCase
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestDetailed
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestAddTaskViewData
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestHeaderViewData
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestListItemViewData
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestTaskViewData
import com.flaringapp.testingsimulator.admin.presentation.tests.AdminTestStatusIsEditableTransformer
import com.flaringapp.testingsimulator.admin.presentation.tests.AdminTestStatusNameTransformer
import com.flaringapp.testingsimulator.core.app.common.launchOnIO
import com.flaringapp.testingsimulator.core.app.common.tryAdd
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.LiveDataList
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.MutableLiveDataList
import com.flaringapp.testingsimulator.domain.features.taxonomy.TaxonomyFormatter
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel

abstract class AdminTestViewModel : BaseViewModel() {

    abstract val nameLiveData: LiveData<String>

    abstract val listItemsLiveData: LiveDataList<AdminTestListItemViewData>

    abstract fun init(
        testId: Int,
        testName: String,
    )

    abstract fun openTask()

    abstract fun createTask()

}

class AdminTestViewModeImpl(
    private val getTestDetailedUseCase: GetAdminTestDetailedUseCase,
    private val testStatusNameTransformer: AdminTestStatusNameTransformer,
    private val testStatusIsEditableTransformer: AdminTestStatusIsEditableTransformer,
    private val textProvider: TextProvider,
    private val taxonomyFormatter: TaxonomyFormatter,
) : AdminTestViewModel() {

    override val nameLiveData = MutableLiveData<String>()

    override val listItemsLiveData = MutableLiveDataList<AdminTestListItemViewData>()

    private var test: AdminTestDetailed? = null

    override fun init(testId: Int, testName: String) {
        nameLiveData.value = testName
        loadTest(testId)
    }

    override fun openTask() {
        // TODO admin test open task
    }

    override fun createTask() {
        // TODO admin test create task
    }

    private fun loadTest(id: Int) {
        viewModelScope.launchOnIO {
            val loadedTest = safeCall {
                getTestDetailedUseCase(id)
            } ?: return@launchOnIO

            test = loadedTest

            val items = composeListItemsViewData(loadedTest)

            withMainContext {
                listItemsLiveData.value = items
            }
        }
    }

    private fun composeListItemsViewData(
        test: AdminTestDetailed
    ): List<AdminTestListItemViewData> {
        val items = mutableListOf<AdminTestListItemViewData>()
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

    private fun composeHeaderViewData(test: AdminTestDetailed): AdminTestHeaderViewData {
        val statusNameWithStatistics = LinkedHashMap<CharSequence, CharSequence>(
            test.statistics.size + 1
        )
        val statusLabel = textProvider.getText(R.string.task_status)
        val statusName = test.status.transform(testStatusNameTransformer)

        statusNameWithStatistics[statusLabel] = statusName
        statusNameWithStatistics.putAll(test.statistics)

        return AdminTestHeaderViewData(
            name = test.name,
            statusAndStatistics = taxonomyFormatter.format(statusNameWithStatistics)
        )
    }

    private fun composeTasksViewData(test: AdminTestDetailed): List<AdminTestTaskViewData> {
        return test.tasks.map { task ->
            val difficulty = "I".repeat(task.difficultyLevel)
            AdminTestTaskViewData(
                id = task.id,
                text = taxonomyFormatter.format(task.name, difficulty),
            )
        }
    }

    private fun composeAddTaskViewData(test: AdminTestDetailed): AdminTestAddTaskViewData? {
        val isEditable = test.status.transform(testStatusIsEditableTransformer)
        if (!isEditable) return null
        return AdminTestAddTaskViewData
    }

}