package com.flaringapp.testingsimulator.presentation.features.profile

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.flaringapp.testingsimulator.core.presentation.appbar.configuration.configureAppBarWithLifecycle
import com.flaringapp.testingsimulator.core.presentation.utils.textWithVisibility
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

    private var taxonomyFormatter: ProfileTaxonomyFormatter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        taxonomyFormatter = ProfileTaxonomyFormatter(requireContext())
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        taxonomyFormatter = null
        super.onDestroyView()
    }

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
            setEmail(email)
        }
        studyingLiveData.observe(viewLifecycleOwner) { studying ->
            setStudying(studying)
        }
        workPlaceLiveData.observe(viewLifecycleOwner) { workPlace ->
            setWorkPlace(workPlace)
        }
        roleLiveData.observe(viewLifecycleOwner) { role ->
            setRole(role)
        }
        statisticsLiveData.observe(viewLifecycleOwner) { statistics ->
            adapterAction { it.submitList(statistics) }
        }
    }

    private fun setEmail(email: String) {
        binding.textEmail.textWithVisibility = taxonomyFormatter?.formatTaxonomyText(
            prefixStringRes = R.string.profile_email,
            content = email,
        )
    }

    private fun setStudying(studying: String?) {
        binding.textStudying.textWithVisibility = taxonomyFormatter?.formatTaxonomyText(
            prefixStringRes = R.string.profile_studying,
            content = studying,
        )
    }

    private fun setWorkPlace(workPlace: String?) {
        binding.textWorkPlace.textWithVisibility = taxonomyFormatter?.formatTaxonomyText(
            prefixStringRes = R.string.profile_work_place,
            content = workPlace,
        )
    }

    private fun setRole(role: String?) {
        binding.textRole.textWithVisibility = taxonomyFormatter?.formatTaxonomyText(
            prefixStringRes = R.string.profile_role,
            content = role,
        )
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