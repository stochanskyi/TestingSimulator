package com.flaringapp.testingsimulator.admin.presentation.test_details

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.admin.databinding.FragmentAdminTestDetailsBinding
import com.flaringapp.testingsimulator.admin.presentation.test_details.adapter.AdminTestDetailsItemsAdapter
import com.flaringapp.testingsimulator.core.presentation.appbar.configuration.updateAppBarConfiguration
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminTestDetailsFragment : ModelledFragment(R.layout.fragment_admin_test_details) {

    override val model: AdminTestDetailsViewModel by viewModel()

    private val binding: FragmentAdminTestDetailsBinding by viewBinding(
        FragmentAdminTestDetailsBinding::bind
    )

    private val args: AdminTestDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.init(args.testId, args.testName)
    }

    override fun initViews() = with(binding) {
        recyclerListItems.layoutManager = LinearLayoutManager(requireContext())
        recyclerListItems.adapter = AdminTestDetailsItemsAdapter(
            openTask = model::openTask,
            addTask = model::createTask
        )
        recyclerListItems.addItemDecoration(
            AdminTestDetailsTaskSpacingDecoration()
        )
    }

    override fun observeModel() = with(model) {
        nameLiveData.observe(viewLifecycleOwner) { title ->
            updateAppBarConfiguration {
                this.title = title
            }
        }
        listItemsLiveData.observe(viewLifecycleOwner) { items ->
            adapterAction { it.submitList(items) }
        }
    }

    private fun <T> adapterAction(action: (AdminTestDetailsItemsAdapter) -> T): T {
        val adapter = binding.recyclerListItems.adapter as AdminTestDetailsItemsAdapter
        return adapter.let(action)
    }

}