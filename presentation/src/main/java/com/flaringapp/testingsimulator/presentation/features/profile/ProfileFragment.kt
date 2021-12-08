package com.flaringapp.testingsimulator.presentation.features.profile

import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.databinding.FragmentProfileBinding
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : ModelledFragment(R.layout.fragment_profile) {

    override val model: ProfileViewModel by viewModel()

    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)

}