package com.flaringapp.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment {

    constructor() : super()

    @ContentView
    constructor (@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    protected open fun initViews() {
    }

    protected open fun BaseViewModelContract.observeErrors() {
        errorData.observe(viewLifecycleOwner) {
            context?.showMessageDialog(it)
        }
    }

}