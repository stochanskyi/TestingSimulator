package com.flaringapp.testingsimulator.admin.presentation.task_edit

import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel

abstract class AdminTaskEditViewModel : BaseViewModel() {

    abstract fun setBlockText(id: Int, text: String)

    abstract fun setBlockEnabled(id: Int, active: Boolean)

    abstract fun changeBlockPosition(id: Int, oldPosition: Int, newPosition: Int)

    abstract fun linkBlock(id: Int)

    abstract fun removeBlock(id: Int)

    abstract fun saveChanges()

}

class AdminTaskEditViewModelImpl : AdminTaskEditViewModel() {

    override fun setBlockText(id: Int, text: String) {
        // TODO impl
    }

    override fun setBlockEnabled(id: Int, active: Boolean) {
        // TODO impl
    }

    override fun changeBlockPosition(id: Int, oldPosition: Int, newPosition: Int) {
        // TODO impl
    }

    override fun linkBlock(id: Int) {
        // TODO impl
    }

    override fun removeBlock(id: Int) {
        // TODO impl
    }

    override fun saveChanges() {
        // TODO impl
    }
}