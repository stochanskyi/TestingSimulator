package com.flaringapp.testingsimulator.presentation.features.tests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent
import com.flaringapp.testingsimulator.presentation.features.tests.models.TestViewData
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel

abstract class TestsViewModel : BaseViewModel() {
    abstract val loadingLiveData: LiveData<Boolean>
    abstract val testsLiveData: LiveData<List<TestViewData>>
    abstract val openTestLiveData: LiveData<Int>

    abstract fun init(topicId: Int)

    abstract fun openTest(testId: Int)

    abstract fun addTest()
}

class TestsViewModelImpl : TestsViewModel() {

    override val loadingLiveData: LiveData<Boolean> = MutableLiveData(false)
    override val testsLiveData = MutableLiveData<List<TestViewData>>()
    override val openTestLiveData = SingleLiveEvent<Int>()

    override fun init(topicId: Int) {
        //TODO load topics
    }

    override fun openTest(testId: Int) {
        openTestLiveData.value = testId
    }

    override fun addTest() {
        //TODO implement
    }


}