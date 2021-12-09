package com.flaringapp.testingsimulator.presentation.features.tests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent
import com.flaringapp.testingsimulator.presentation.features.tests.adapter.args.TopicPreliminaryData
import com.flaringapp.testingsimulator.presentation.features.tests.models.TestViewData
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel

abstract class TestsViewModel : BaseViewModel() {
    abstract val loadingLiveData: LiveData<Boolean>
    abstract val testsLiveData: LiveData<List<TestViewData>>
    abstract val openTestLiveData: LiveData<Int>

    abstract val topicNameLiveData: LiveData<String>

    abstract fun init(topicData: TopicPreliminaryData)

    abstract fun openTest(testId: Int)

    abstract fun addTest()
}

class TestsViewModelImpl : TestsViewModel() {

    override val loadingLiveData: LiveData<Boolean> = MutableLiveData(false)
    override val testsLiveData = MutableLiveData<List<TestViewData>>()
    override val openTestLiveData = SingleLiveEvent<Int>()

    override val topicNameLiveData = MutableLiveData<String>()

    private var topicId: Int = 0
    private var name: String = ""

    override fun init(topicData: TopicPreliminaryData) {
        topicId = topicData.id
        name = topicData.name

        topicNameLiveData.value = name
    }

    override fun openTest(testId: Int) {
        openTestLiveData.value = testId
    }

    override fun addTest() {
        //TODO implement
    }

}