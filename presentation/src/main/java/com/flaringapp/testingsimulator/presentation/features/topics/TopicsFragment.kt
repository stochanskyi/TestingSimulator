package com.flaringapp.testingsimulator.presentation.features.topics

import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.core.presentation.appbar.configuration.configureAppBarWithLifecycle
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.databinding.FragmentTopicsBinding
import com.flaringapp.testingsimulator.presentation.features.topics.adapter.TopicsAdapter
import com.flaringapp.testingsimulator.presentation.features.topics.navigation.TopicsNavigator
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopicsFragment : ModelledFragment(R.layout.fragment_topics) {

    override val model: TopicsViewModel by viewModel()

    private val binding by viewBinding { FragmentTopicsBinding.bind(it) }

    private val topicsNavigator: TopicsNavigator by inject()

    override fun initViews() = with(binding) {
        val topicsAdapter = TopicsAdapter(model::openTopic)

        topicsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        topicsRecyclerView.adapter = topicsAdapter

        topicsRecyclerView.addItemDecoration(createItemDecoration())

        configureAppBarWithLifecycle {
            menuId = R.menu.topics_menu
            itemSelectedListener = this@TopicsFragment::onMenuItemSelected
        }
    }

    private fun createItemDecoration(): RecyclerView.ItemDecoration {
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.bg_separator_padded)?.let {
            decoration.setDrawable(it)
        }
        return decoration
    }

    private fun onMenuItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_profile -> {
                model.openProfile()
                true
            }
            else -> false
        }
    }

    override fun observeModel() {
        model.loadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }

        model.topicsLiveData.observe(viewLifecycleOwner) {
            binding.topicsRecyclerView.topicsAdapter?.setData(it)
        }

        model.openProfileLiveData.observe(viewLifecycleOwner) {
            topicsNavigator.navigateToProfile(findNavController())
        }
    }

    private val RecyclerView.topicsAdapter: TopicsAdapter?
        get() = adapter as? TopicsAdapter

}