package com.flaringapp.testingsimulator.admin.presentation.test

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.admin.databinding.FragmentAdminTestBinding
import com.flaringapp.testingsimulator.admin.presentation.test.adapter.AdminTestItemsAdapter
import com.flaringapp.testingsimulator.core.presentation.appbar.configuration.updateAppBarConfiguration
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminTestFragment : ModelledFragment(R.layout.fragment_admin_test) {

    override val model: AdminTestViewModel by viewModel()

    private val binding: FragmentAdminTestBinding by viewBinding(FragmentAdminTestBinding::bind)

    private val args: AdminTestFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.init(args.testId, args.testName)
    }

    override fun initViews() = with(binding) {
        recyclerListItems.layoutManager = LinearLayoutManager(requireContext())
        recyclerListItems.adapter = AdminTestItemsAdapter(
            openTask = model::openTask,
            addTask = model::createTask
        )
        recyclerListItems.addItemDecoration(
            AdminTestItemsTaskSpacingDecoration()
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

    private fun <T> adapterAction(action: (AdminTestItemsAdapter) -> T): T {
        val adapter = binding.recyclerListItems.adapter as AdminTestItemsAdapter
        return adapter.let(action)
    }

}