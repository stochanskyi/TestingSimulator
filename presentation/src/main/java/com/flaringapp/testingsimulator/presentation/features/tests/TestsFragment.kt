package com.flaringapp.testingsimulator.presentation.features.tests

import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.databinding.FragmentTestsBinding
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestsFragment : ModelledFragment(R.layout.fragment_tests) {

    override val model: TestsViewModel by viewModel()

    private val binding: FragmentTestsBinding by viewBinding { FragmentTestsBinding.bind(it) }

}