package com.flaringapp.testingsimulator.admin.presentation.task_edit

import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.admin.databinding.FragmentAdminTaskEditBinding
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminTaskEditFragment : ModelledFragment(R.layout.fragment_admin_task_edit) {

    override val model: AdminTaskEditViewModel by viewModel()

    private val binding: FragmentAdminTaskEditBinding by viewBinding(
        FragmentAdminTaskEditBinding::bind
    )

}