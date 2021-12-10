package com.flaringapp.testingsimulator.presentation.features.tests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.presentation.utils.isRunning
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent
import com.flaringapp.testingsimulator.core.presentation.utils.startLoadingTask
import com.flaringapp.testingsimulator.presentation.features.tests.adapter.args.TopicPreliminaryData
import com.flaringapp.testingsimulator.presentation.features.tests.behaviour.TestsBehaviour
import com.flaringapp.testingsimulator.presentation.features.tests.models.TestDetailNavArgs
import com.flaringapp.testingsimulator.presentation.features.tests.models.TestViewData
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel
import kotlinx.coroutines.Job

abstract class TestsViewModel : BaseViewModel() {

    abstract val loadingLiveData: LiveData<Boolean>

    abstract val testsLiveData: LiveData<List<TestViewData>>

    abstract val openTestLiveData: LiveData<TestDetailNavArgs>

    abstract val topicNameLiveData: LiveData<String>

    abstract fun init(topicData: TopicPreliminaryData)

    abstract fun openTest(testId: Int)

    abstract fun addTest()

}

class TestsViewModelImpl(
    private val behaviour: TestsBehaviour,
) : TestsViewModel() {

    override val loadingLiveData = MutableLiveData(false)

    override val testsLiveData = MutableLiveData<List<TestViewData>>()

    override val openTestLiveData = SingleLiveEvent<TestDetailNavArgs>()

    override val topicNameLiveData = MutableLiveData<String>()

    private var topicId: Int = 0
    private var name: String = ""

    private var addTestJob: Job? = null

    override fun init(topicData: TopicPreliminaryData) {
        topicId = topicData.id
        name = topicData.name

        topicNameLiveData.value = name

        loadTests()
    }

    override fun openTest(testId: Int) {
        val test = behaviour.getTest(testId) ?: return
        openTestLiveData.value = TestDetailNavArgs(test.id, test.name)
    }

    override fun addTest() {
        if (addTestJob.isRunning) return

        addTestJob = viewModelScope.startLoadingTask(loadingLiveData) {
            val test = safeCall {
                behaviour.createTest(topicId)
            } ?: return@startLoadingTask

            withMainContext {
                openTest(test.id)
            }
        }
    }

    private fun loadTests() {
        if (addTestJob.isRunning) return

        viewModelScope.startLoadingTask(loadingLiveData) {
            val testsViewData = safeCall {
                behaviour.getTests(topicId)
            } ?: return@startLoadingTask

            withMainContext {
                testsLiveData.value = testsViewData
            }
        }
    }

}