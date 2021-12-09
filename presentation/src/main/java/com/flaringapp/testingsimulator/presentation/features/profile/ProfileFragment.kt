package com.flaringapp.testingsimulator.presentation.features.profile

import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.flaringapp.testingsimulator.core.presentation.appbar.configuration.configureAppBarWithLifecycle
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.databinding.FragmentProfileBinding
import com.flaringapp.testingsimulator.presentation.features.profile.adapter.ProfileStatisticsAdapter
import com.flaringapp.testingsimulator.presentation.features.profile.navigation.ProfileNavigator
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : ModelledFragment(R.layout.fragment_profile) {

    override val model: ProfileViewModel by viewModel()

    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)

    private val navigator: ProfileNavigator by inject()

    override fun initViews() = with(binding) {
        recyclerStatistics.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerStatistics.adapter = ProfileStatisticsAdapter()
        recyclerStatistics.addItemDecoration(
            ProfileStatisticsItemDecoration()
        )

        configureAppBarWithLifecycle {
            menuId = R.menu.profile
            itemSelectedListener = ::onMenuItemSelected
        }
    }

    override fun observeModel() = with(model) {
        nameLiveData.observe(viewLifecycleOwner) { name ->
            binding.textName.text = name
        }
        emailLiveData.observe(viewLifecycleOwner) { email ->
            binding.textEmail.text = email
        }
        studyingLiveData.observe(viewLifecycleOwner) { studying ->
            binding.textStudying.text = studying
        }
        workPlaceLiveData.observe(viewLifecycleOwner) { workPlace ->
            binding.textWorkPlace.text = workPlace
        }
        roleLiveData.observe(viewLifecycleOwner) { role ->
            binding.textRole.text = role
        }

        statisticsLiveData.observe(viewLifecycleOwner) { statistics ->
            adapterAction { it.submitList(statistics) }
        }
    }

    private fun onMenuItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_edit_profile -> {
                openEditProfile()
            }
            else -> return false
        }
        return true
    }

    private fun openEditProfile() {
        navigator.navigateToEditProfile(findNavController())
    }

    private inline fun <reified T> adapterAction(action: (ProfileStatisticsAdapter) -> T): T {
        val adapter = binding.recyclerStatistics.adapter as ProfileStatisticsAdapter
        return action(adapter)
    }
}