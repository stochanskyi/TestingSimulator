package com.flaringapp.testingsimulator.admin.presentation.task_view

import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.admin.databinding.FragmentAdminTaskViewBinding
import com.flaringapp.testingsimulator.admin.presentation.task_view.adapter.AdminViewTaskBlocksAdapter
import com.flaringapp.testingsimulator.core.presentation.appbar.configuration.updateAppBarConfiguration
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminViewTaskFragment : ModelledFragment(R.layout.fragment_admin_task_view) {

    override val model: AdminViewTaskViewModel by viewModel()

    private val binding: FragmentAdminTaskViewBinding by viewBinding(
        FragmentAdminTaskViewBinding::bind
    )

    private val args: AdminViewTaskFragmentArgs by navArgs()

    override fun initViews() = with(binding) {
        blocksRecycler.layoutManager = LinearLayoutManager(requireContext())
        blocksRecycler.adapter = AdminViewTaskBlocksAdapter()
        blocksRecycler.addItemDecoration(
            AdminViewTaskBlocksSpacingDecoration()
        )

        model.init(
            taskId = args.taskId,
            taskName = args.taskName,
        )
    }

    override fun observeModel() = with(model) {
        nameLiveData.observe(viewLifecycleOwner) { name ->
            updateAppBarConfiguration {
                title = name
            }
        }
        loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }
        blocksLiveData.observe(viewLifecycleOwner) { blocks ->
            adapterAction { it.submitList(blocks) }
        }
    }

    private fun <T> adapterAction(action: (AdminViewTaskBlocksAdapter) -> T): T {
        val adapter = binding.blocksRecycler.adapter as AdminViewTaskBlocksAdapter
        return adapter.let(action)
    }
}