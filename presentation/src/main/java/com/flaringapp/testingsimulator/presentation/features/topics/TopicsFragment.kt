package com.flaringapp.testingsimulator.presentation.features.topics

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.databinding.FragmentTopicsBinding
import com.flaringapp.testingsimulator.presentation.features.topics.adapter.TopicsAdapter
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopicsFragment : ModelledFragment(R.layout.fragment_topics) {

    override val model : TopicsViewModel by viewModel()

    private val binding by viewBinding { FragmentTopicsBinding.bind(it) }

    override fun initViews() = with(binding) {
        val topicsAdapter = TopicsAdapter(model::openTopic)

        topicsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        topicsRecyclerView.adapter = topicsAdapter
    }

    override fun observeModel() {
        model.loadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }

        model.topicsLiveData.observe(viewLifecycleOwner) {
            binding.topicsRecyclerView.topicsAdapter?.setData(it)
        }
    }

    private val RecyclerView.topicsAdapter: TopicsAdapter?
        get() = adapter as? TopicsAdapter
}