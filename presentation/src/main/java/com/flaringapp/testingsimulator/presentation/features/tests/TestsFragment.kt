package com.flaringapp.testingsimulator.presentation.features.tests

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flaringapp.testingsimulator.core.presentation.appbar.configuration.configureAppBarWithLifecycle
import com.flaringapp.testingsimulator.core.presentation.utils.debounce.Debouncer
import com.flaringapp.testingsimulator.core.presentation.utils.debounce.NavigationDebouncer
import com.flaringapp.testingsimulator.core.presentation.utils.debounce.observeDebounced
import com.flaringapp.testingsimulator.core.presentation.utils.debounce.setOnClickListenerDebounced
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.common.recycler.LineItemDecoration
import com.flaringapp.testingsimulator.presentation.databinding.FragmentTestsBinding
import com.flaringapp.testingsimulator.presentation.features.tests.adapter.TestsAdapter
import com.flaringapp.testingsimulator.presentation.features.tests.adapter.args.TestsFragmentArgsHandler
import com.flaringapp.testingsimulator.presentation.features.tests.navigation.TestsNavigator
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestsFragment : ModelledFragment(R.layout.fragment_tests) {

    override val model: TestsViewModel by viewModel()

    private val binding: FragmentTestsBinding by viewBinding { FragmentTestsBinding.bind(it) }

    private val debouncer: Debouncer by NavigationDebouncer

    private val paramsHandler: TestsFragmentArgsHandler by inject()

    private val testsNavigator: TestsNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paramsHandler.handleParams(arguments ?: return)
    }

    override fun initViews() = with(binding) {
        paramsHandler.topicPreliminaryData?.let {
            model.init(it)
        }

        testsRecyclerView.layoutManager = LinearLayoutManager(context)
        testsRecyclerView.adapter = TestsAdapter(model::openTest)
        testsRecyclerView.addItemDecoration(LineItemDecoration(context))

        addTestButton.setOnClickListenerDebounced(debouncer) {
            model.addTest()
        }
    }

    override fun observeModel() {
        model.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        model.testsLiveData.observe(viewLifecycleOwner) { tests ->
            adapterAction { it.submitList(tests) }
        }

        model.topicNameLiveData.observe(viewLifecycleOwner) { title ->
            configureAppBarWithLifecycle {
                this.title = title
            }
        }

        model.openTestLiveData.observeDebounced(viewLifecycleOwner, debouncer) {
            testsNavigator.navigateToTest(findNavController(), it.id, it.name)
        }
    }

    private inline fun <T> adapterAction(action: (TestsAdapter) -> T): T {
        val adapter = binding.testsRecyclerView.adapter as TestsAdapter
        return adapter.let(action)
    }
}