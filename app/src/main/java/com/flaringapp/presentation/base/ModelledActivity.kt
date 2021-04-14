package com.flaringapp.presentation.base

import android.os.Bundle
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import com.flaringapp.presentation.base.BaseActivity
import com.flaringapp.presentation.base.BaseViewModelContract

abstract class ModelledActivity : BaseActivity {

    constructor() : super()

    @ContentView
    constructor (@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected abstract val model: BaseViewModelContract

    protected open val observeErrors: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeModel()
        if (observeErrors) {
            model.observeErrors()
        }
    }

    protected open fun observeModel() {
    }

}