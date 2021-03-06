package com.flaringapp.testingsimulator.presentation.features.topics

import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flaringapp.testingsimulator.core.presentation.appbar.configuration.configureAppBarWithLifecycle
import com.flaringapp.testingsimulator.core.presentation.utils.debounce.Debouncer
import com.flaringapp.testingsimulator.core.presentation.utils.debounce.NavigationDebouncer
import com.flaringapp.testingsimulator.core.presentation.utils.debounce.observeDebounced
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.common.recycler.LineItemDecoration
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

    private val debouncer: Debouncer by NavigationDebouncer

    private val navigator: TopicsNavigator by inject()

    override fun initViews() = with(binding) {
        topicsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        topicsRecyclerView.adapter = TopicsAdapter(model::openTopic)
        topicsRecyclerView.addItemDecoration(LineItemDecoration(context))

        configureAppBarWithLifecycle {
            menuId = R.menu.topics_menu
            itemSelectedListener = this@TopicsFragment::onMenuItemSelected
        }
    }

    override fun observeModel() {
        model.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        model.topicsLiveData.observe(viewLifecycleOwner) { topics ->
            adapterAction { it.submitList(topics) }
        }

        model.openProfileLiveData.observeDebounced(viewLifecycleOwner, debouncer) {
            navigator.navigateToProfile(findNavController())
        }

        model.openTestsLiveData.observeDebounced(viewLifecycleOwner, debouncer) { topicData ->
            navigator.navigateToTests(findNavController(), topicData)
        }
    }

    private fun onMenuItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_profile -> {
                model.openProfile()
            }
            else -> return false
        }
        return true
    }

    private inline fun <T> adapterAction(action: (TopicsAdapter) -> T): T {
        val adapter = binding.topicsRecyclerView.adapter as TopicsAdapter
        return adapter.let(action)
    }
}