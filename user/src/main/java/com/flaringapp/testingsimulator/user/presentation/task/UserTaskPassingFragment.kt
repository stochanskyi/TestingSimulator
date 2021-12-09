package com.flaringapp.testingsimulator.user.presentation.task

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.flaringapp.testingsimulator.user.R
import com.flaringapp.testingsimulator.user.databinding.FragmentUserTaskPassingBinding
import com.flaringapp.testingsimulator.user.presentation.task.adapter.UserTaskPassingBlockTouchCallback
import com.flaringapp.testingsimulator.user.presentation.task.adapter.UserTaskPassingBlockTouchDragListener
import com.flaringapp.testingsimulator.user.presentation.task.adapter.UserTaskPassingBlocksAdapter
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserTaskPassingFragment : ModelledFragment(R.layout.fragment_user_task_passing) {

    override val model: UserTaskPassingViewModel by viewModel()

    private val binding: FragmentUserTaskPassingBinding by viewBinding(
        FragmentUserTaskPassingBinding::bind
    )

    override fun initViews() = with(binding) {
        recyclerBlocks.layoutManager = LinearLayoutManager(requireContext())
        recyclerBlocks.addItemDecoration(
            UserTaskPassingBlockItemDecoration()
        )

        val touchCallback = UserTaskPassingBlockTouchCallback()
        val itemTouchHelper = ItemTouchHelper(touchCallback)

        val adapter = UserTaskPassingBlocksAdapter(
            dragListener = createTouchDragListener(itemTouchHelper),
            onEnabledChanged = { _, _ -> },
            onMove = { _, _, _ -> },
        )
        recyclerBlocks.adapter = adapter
        touchCallback.attach(adapter)
        itemTouchHelper.attachToRecyclerView(recyclerBlocks)
    }

    private fun createTouchDragListener(
        itemTouchHelper: ItemTouchHelper
    ): UserTaskPassingBlockTouchDragListener {
        return object : UserTaskPassingBlockTouchDragListener {
            override fun beginTouchDrag(holder: RecyclerView.ViewHolder): Boolean {
                itemTouchHelper.startDrag(holder)
                return true
            }
        }
    }
}