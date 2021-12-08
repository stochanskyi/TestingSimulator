package com.flaringapp.testingsimulator.presentation.features.topics

import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopicsFragment : ModelledFragment(R.layout.fragment_topics) {

    override val model : TopicsViewModel by viewModel()

}