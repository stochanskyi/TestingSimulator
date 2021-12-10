package com.flaringapp.testingsimulator.admin.presentation.task_view

import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.admin.databinding.FragmentAdminTaskViewBinding
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminTaskViewFragment : ModelledFragment(R.layout.fragment_admin_task_view) {

    override val model: AdminViewTaskViewModel by viewModel()

    private val binding: FragmentAdminTaskViewBinding by viewBinding(
        FragmentAdminTaskViewBinding::bind
    )

}