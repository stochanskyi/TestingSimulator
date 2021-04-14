package com.flaringapp.presentation.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment

abstract class BaseDialog : DialogFragment {

    constructor() : super()

    @ContentView
    constructor (@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            window?.decorView?.background?.alpha = 0
        }
    }

    protected open fun initViews() {
    }

    protected open fun BaseViewModelContract.observeErrors() {
        errorData.observe(viewLifecycleOwner) {
            context?.showMessageDialog(it)
        }
    }

}