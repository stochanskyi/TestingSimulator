package com.flaringapp.testingsimulator.presentation.mvvm

import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes

abstract class ModelledDialog : BaseDialog {

    constructor() : super()

    @ContentView
    constructor (@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected abstract val model: BaseViewModelContract

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeModel()
        model.observeErrors()
    }

    protected open fun observeModel() = Unit

}