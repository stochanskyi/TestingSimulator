package com.flaringapp.presentation.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment

abstract class BaseDialog : DialogFragment {

    constructor() : super()

    @ContentView
    constructor (@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            window?.decorView?.background?.alpha = 0
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        setupBackground(requireView())
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        val currentHeight = dialog?.window?.attributes?.height

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            currentHeight ?: WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    protected open fun initViews() {
    }

    protected open fun setupBackground(view: View) {
        setupRoundedBackground(view)
    }

    protected open fun BaseViewModelContract.observeErrors() {
        errorData.observe(viewLifecycleOwner) {
            handleErrorInternal(it)
        }
    }
}