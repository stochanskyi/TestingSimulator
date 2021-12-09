package com.flaringapp.testingsimulator.user.presentation.task

import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.flaringapp.testingsimulator.user.R
import com.flaringapp.testingsimulator.user.databinding.FragmentUserTestDetailsBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserTaskPassingFragment : ModelledFragment(R.layout.fragment_user_task_passing) {

    override val model: UserTaskPassingViewModel by viewModel()

    private val binding: FragmentUserTestDetailsBinding by viewBinding(
        FragmentUserTestDetailsBinding::bind
    )
}