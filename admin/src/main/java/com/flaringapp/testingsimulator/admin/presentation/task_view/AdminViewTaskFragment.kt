package com.flaringapp.testingsimulator.admin.presentation.task_view

import androidx.recyclerview.widget.LinearLayoutManager
import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.admin.databinding.FragmentAdminTaskViewBinding
import com.flaringapp.testingsimulator.admin.presentation.task_view.adapter.AdminViewTaskBlocksAdapter
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminViewTaskFragment : ModelledFragment(R.layout.fragment_admin_task_view) {

    override val model: AdminViewTaskViewModel by viewModel()

    private val binding: FragmentAdminTaskViewBinding by viewBinding(
        FragmentAdminTaskViewBinding::bind
    )

    override fun initViews() = with(binding) {
        blocksRecycler.layoutManager = LinearLayoutManager(requireContext())
        blocksRecycler.adapter = AdminViewTaskBlocksAdapter()
        blocksRecycler.addItemDecoration(
            AdminViewTaskBlocksSpacingDecoration()
        )
    }

    override fun observeModel() = with(model) {
    }

    private fun <T> adapterAction(action: (AdminViewTaskBlocksAdapter) -> T) : T {
        val adapter = binding.blocksRecycler.adapter as AdminViewTaskBlocksAdapter
        return adapter.let(action)
    }
}