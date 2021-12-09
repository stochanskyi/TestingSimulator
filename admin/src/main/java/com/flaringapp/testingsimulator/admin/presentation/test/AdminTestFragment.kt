package com.flaringapp.testingsimulator.admin.presentation.test

import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminTestFragment : ModelledFragment(R.layout.fragment_admin_test) {

    override val model: AdminTestViewModel by viewModel()

}