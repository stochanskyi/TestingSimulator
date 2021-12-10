package com.flaringapp.testingsimulator.user.presentation.tests.testDetails

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.flaringapp.testingsimulator.core.presentation.appbar.configuration.configureAppBarWithLifecycle
import com.flaringapp.testingsimulator.core.presentation.utils.textWithInvisibility
import com.flaringapp.testingsimulator.core.presentation.utils.textWithVisibility
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.flaringapp.testingsimulator.user.R
import com.flaringapp.testingsimulator.user.databinding.FragmentUserTestDetailsBinding
import com.flaringapp.testingsimulator.user.presentation.tests.testDetails.models.UserTestStatusViewData
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserTestDetailsFragment : ModelledFragment(R.layout.fragment_user_test_details) {

    override val model: UserTestDetailsViewModel by viewModel()

    private val binding: FragmentUserTestDetailsBinding by viewBinding {
        FragmentUserTestDetailsBinding.bind(it)
    }

    private val args: UserTestDetailsFragmentArgs by navArgs()

    override fun initViews() {
        model.init(args.testId, args.testName)

        binding.testLaunchButton.setOnClickListener { model.launchTest() }
    }

    override fun observeModel() {
        model.loadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }

        model.testNameLiveData.observe(viewLifecycleOwner) {
            binding.nameTextView.text = it
            configureAppBarWithLifecycle { title = it }
        }

        model.testStateLiveData.observe(viewLifecycleOwner) {
            applyTestState(it)
        }

        model.testStatisticsLiveData.observe(viewLifecycleOwner) {
            binding.statisticsTextView.text = it
        }

        model.openTasksLiveData.observe(viewLifecycleOwner) { args ->
            val action = UserTestDetailsFragmentDirections
                .actionFragmentUserTestDetailsToFragmentUserTaskPassing(
                    testId = args.testId,
                    tasksCount = args.tasksCount,
                    taskId = args.taskId ?: -1,
                )

            findNavController().navigate(action)
        }
    }

    private fun applyTestState(state: UserTestStatusViewData) = with(binding) {
        stateTextView.background.setTint(state.statusColor)
        stateTextView.textWithInvisibility = state.statusName

        emojiImageView.setImageResource(state.statusEmojiRes)

        testLaunchButton.textWithInvisibility = state.buttonLabel
        testLaunchButton.isEnabled = testLaunchButton.isVisible
    }
}