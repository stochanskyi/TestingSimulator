package com.flaringapp.testingsimulator.presentation.features.tests

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.common.recycler.LineItemDecoration
import com.flaringapp.testingsimulator.presentation.databinding.FragmentTestsBinding
import com.flaringapp.testingsimulator.presentation.features.tests.adapter.TestsAdapter
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestsFragment : ModelledFragment(R.layout.fragment_tests) {

    override val model: TestsViewModel by viewModel()

    private val binding: FragmentTestsBinding by viewBinding { FragmentTestsBinding.bind(it) }

    override fun initViews() = with(binding) {
        testsRecyclerView.layoutManager = LinearLayoutManager(context)
        testsRecyclerView.adapter = TestsAdapter(model::openTest)
        testsRecyclerView.addItemDecoration(LineItemDecoration(context))

        addTestButton.setOnClickListener { model.addTest() }
    }

    override fun observeModel() {
        model.loadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }

        model.testsLiveData.observe(viewLifecycleOwner) {
            binding.testsRecyclerView.testsAdapter?.setData(it)
        }

        model.openTestLiveData.observe(viewLifecycleOwner) {
            //TODO navigate to test
        }
    }

    private val RecyclerView.testsAdapter: TestsAdapter?
        get() = adapter as? TestsAdapter
}