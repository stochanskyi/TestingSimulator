package com.flaringapp.testingsimulator.presentation.mvvm

import android.os.Bundle
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes

abstract class ModelledActivity : BaseActivity {

    constructor() : super()

    @ContentView
    constructor (@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected abstract val model: BaseViewModelContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeModel()
        model.observeErrors()
    }

    protected open fun observeModel() = Unit

}