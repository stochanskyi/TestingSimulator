package com.flaringapp.testingsimulator.presentation.features.edit_profile

import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProfileFragment : ModelledFragment(R.layout.fragment_edit_profile) {

    override val model: EditProfileViewModel by viewModel()

}