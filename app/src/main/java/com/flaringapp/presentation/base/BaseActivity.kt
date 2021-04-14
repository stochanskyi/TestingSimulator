package com.flaringapp.presentation.base

import android.os.Bundle
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity {

    constructor() : super()

    @ContentView
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    protected open fun initViews() {}

    protected open fun BaseViewModelContract.observeErrors() {
        errorData.observe(this@BaseActivity) {
            showMessageDialog(it)
        }
    }

}