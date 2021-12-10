package com.flaringapp.testingsimulator.admin.presentation.test_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.admin.domain.tests.GetAdminTestDetailedUseCase
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestDetailed
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.AdminTestDetailsAddTaskViewData
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.AdminTestDetailsHeaderViewData
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.AdminTestDetailsItemViewData
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.AdminTestDetailsTaskViewData
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

abstract class AdminTestDetailsViewModel : BaseViewModel() {

    abstract val nameLiveData: LiveData<String>

    abstract val listItemsLiveData: LiveDataList<AdminTestDetailsItemViewData>

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
    private val textProvider: TextProvider,
    private val taxonomyFormatter: TaxonomyFormatter,
) : AdminTestDetailsViewModel() {

    override val nameLiveData = MutableLiveData<String>()

    override val listItemsLiveData = MutableLiveDataList<AdminTestDetailsItemViewData>()

    private var test: AdminTestDetailed? = null

    override fun init(testId: Int, testName: String) {
        nameLiveData.value = testName
        loadTest(testId)
    }

    override fun openTask(id: Int) {
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
        val statusLabel = textProvider.getText(R.string.task_status)
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