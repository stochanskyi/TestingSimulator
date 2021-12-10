package com.flaringapp.testingsimulator.admin.presentation.test_details

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.admin.databinding.FragmentAdminTestDetailsBinding
import com.flaringapp.testingsimulator.admin.presentation.test_details.adapter.AdminTestDetailsItemsAdapter
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.AdminTestDetailsOpenViewTaskViewData
import com.flaringapp.testingsimulator.core.presentation.appbar.configuration.updateAppBarConfiguration
import com.flaringapp.testingsimulator.core.presentation.utils.textWithVisibility
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminTestDetailsFragment : ModelledFragment(R.layout.fragment_admin_test_details) {

    override val model: AdminTestDetailsViewModel by viewModel()

    private val binding: FragmentAdminTestDetailsBinding by viewBinding(
        FragmentAdminTestDetailsBinding::bind
    )

    private val args: AdminTestDetailsFragmentArgs by navArgs()

    override fun initViews() = with(binding) {
        model.init(args.testId, args.testName)
        recyclerListItems.layoutManager = LinearLayoutManager(requireContext())
        recyclerListItems.adapter = AdminTestDetailsItemsAdapter(
            openTask = model::openTask,
            addTask = model::createTask
        )
        recyclerListItems.addItemDecoration(
            AdminTestDetailsTaskSpacingDecoration()
        )

        actionButton.setOnClickListener {
            model.moveToNextState()
        }
    }

    override fun observeModel() = with(model) {
        nameLiveData.observe(viewLifecycleOwner) { title ->
            updateAppBarConfiguration {
                this.title = title
            }
        }
        loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }
        listItemsLiveData.observe(viewLifecycleOwner) { items ->
            adapterAction { it.submitList(items) }
        }
        actionLiveData.observe(viewLifecycleOwner) { action ->
            binding.actionButton.textWithVisibility = action
        }
        openViewTaskLiveData.observe(viewLifecycleOwner) { data ->
            openViewTask(data)
        }
        openEditTaskLiveData.observe(viewLifecycleOwner) { data ->
            openEditTask(data)
        }
    }

    private fun openViewTask(data: AdminTestDetailsOpenViewTaskViewData) {
        val action = AdminTestDetailsFragmentDirections.actionFragmentAdminTestToFragmentAdminViewTask(
            taskId = data.taskId,
            taskName = data.taskName,
        )

        findNavController().navigate(action)
    }

    private fun openEditTask(data: AdminTestDetailsOpenEditTaskViewData) {
        val action = AdminTestDetailsFragmentDirections.actionFragmentAdminTestToFragmentAdminTaskEdit(
            taskId = data.taskId ?: -1,
            testId = data.testId
        )
        
        findNavController().navigate(action)
    }

    private fun <T> adapterAction(action: (AdminTestDetailsItemsAdapter) -> T): T {
        val adapter = binding.recyclerListItems.adapter as AdminTestDetailsItemsAdapter
        return adapter.let(action)
    }

}