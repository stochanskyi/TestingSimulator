package com.flaringapp.testingsimulator.admin.presentation.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestListItemViewData
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.LiveDataList
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.MutableLiveDataList
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

class AdminTestViewModeImpl : AdminTestViewModel() {

    override val nameLiveData = MutableLiveData<String>()

    override val listItemsLiveData = MutableLiveDataList<AdminTestListItemViewData>()

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
        // TODO admin test load data
    }
}