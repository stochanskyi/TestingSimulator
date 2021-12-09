package com.flaringapp.testingsimulator.user.presentation.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.LiveDataList
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.MutableLiveDataList
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel
import com.flaringapp.testingsimulator.user.presentation.task.models.UserTaskPassingBlockViewData

abstract class UserTaskPassingViewModel : BaseViewModel() {

    abstract val taskNameLiveData: LiveData<String>
    abstract val testNumberLiveData: LiveData<String>

    abstract val blocksLiveData: LiveDataList<UserTaskPassingBlockViewData>

    abstract fun setBlockEnabled(blockId: Int, isEnabled: Boolean)

    abstract fun changeBlockPosition(blockId: Int, oldPosition: Int, newPosition: Int)

}

class UserTaskPassingViewModelImpl: UserTaskPassingViewModel() {

    override val taskNameLiveData = MutableLiveData<String>()

    override val testNumberLiveData = MutableLiveData<String>()

    override val blocksLiveData = MutableLiveDataList<UserTaskPassingBlockViewData>()

    override fun setBlockEnabled(blockId: Int, isEnabled: Boolean) {
        TODO("Not yet implemented")
    }

    override fun changeBlockPosition(blockId: Int, oldPosition: Int, newPosition: Int) {
        TODO("Not yet implemented")
    }
}