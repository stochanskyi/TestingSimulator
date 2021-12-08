package com.flaringapp.testingsimulator.presentation.features.profile

import android.os.Bundle
import android.view.View
import com.flaringapp.testingsimulator.core.presentation.utils.textWithVisibility
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.databinding.FragmentProfileBinding
import com.flaringapp.testingsimulator.presentation.features.profile.adapter.ProfileStatisticsAdapter
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : ModelledFragment(R.layout.fragment_profile) {

    override val model: ProfileViewModel by viewModel()

    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)

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
        recyclerStatistics.adapter = ProfileStatisticsAdapter()
        recyclerStatistics.addItemDecoration(
            ProfileStatisticsItemDecoration()
        )
    }

    override fun observeModel() = with(model) {
        nameLiveData.observe(viewLifecycleOwner) { name ->
            binding.textName.text = name
        }
        emailLiveData.observe(viewLifecycleOwner) { email ->
            setEmail(email)
        }
        studyingLiveData.observe(viewLifecycleOwner) { studying ->
            setStudyingAt(studying)
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

    private fun setStudyingAt(studying: String?) {
        binding.textStudyingAt.textWithVisibility = taxonomyFormatter?.formatTaxonomyText(
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

    private inline fun <reified T> adapterAction(action: (ProfileStatisticsAdapter) -> T): T {
        val adapter = binding.recyclerStatistics.adapter as ProfileStatisticsAdapter
        return action(adapter)
    }

}