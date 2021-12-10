package com.flaringapp.testingsimulator.admin.presentation.task_edit

import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.admin.databinding.FragmentAdminTaskEditBinding
import com.flaringapp.testingsimulator.admin.presentation.task_edit.adapter.AdminTaskEditBlockTouchCallback
import com.flaringapp.testingsimulator.admin.presentation.task_edit.adapter.AdminTaskEditBlocksAdapter
import com.flaringapp.testingsimulator.core.presentation.utils.doOnDoneClicked
import com.flaringapp.testingsimulator.core.presentation.utils.hideKeyboard
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.observeOnce
import com.flaringapp.testingsimulator.presentation.common.recycler.drag.TouchDragListener
import com.flaringapp.testingsimulator.presentation.mvvm.ModelledFragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminTaskEditFragment : ModelledFragment(R.layout.fragment_admin_task_edit) {

    override val model: AdminTaskEditViewModel by viewModel()

    private val binding: FragmentAdminTaskEditBinding by viewBinding(
        FragmentAdminTaskEditBinding::bind
    )

    override fun initViews() = with(binding) {
        nameEditText.doAfterTextChanged {
            model.setName(it.toString())
        }
        nameEditText.doOnDoneClicked {
            hideKeyboard()
        }

        recyclerBlocks.layoutManager = LinearLayoutManager(requireContext())
        recyclerBlocks.addItemDecoration(
            AdminTaskEditBlockItemDecoration()
        )

        val touchCallback = AdminTaskEditBlockTouchCallback()
        val itemTouchHelper = ItemTouchHelper(touchCallback)

        val adapter = AdminTaskEditBlocksAdapter(
            dragListener = createTouchDragListener(itemTouchHelper),
            onBlockTextChanged = model::setBlockText,
            onBlockActiveChanged = model::setBlockEnabled,
            onMove = model::changeBlockPosition,
            onLink = model::linkBlock,
            onRemove = model::removeBlock,
        )
        recyclerBlocks.adapter = adapter
        touchCallback.attach(adapter)
        itemTouchHelper.attachToRecyclerView(recyclerBlocks)

        saveChangesButton.setOnClickListener {
            model.saveChanges()
        }
    }

    private fun createTouchDragListener(
        itemTouchHelper: ItemTouchHelper
    ): TouchDragListener {
        return object : TouchDragListener {
            override fun beginTouchDrag(holder: RecyclerView.ViewHolder): Boolean {
                itemTouchHelper.startDrag(holder)
                return true
            }
        }
    }

    private fun <T> adapterAction(action: (AdminTaskEditBlocksAdapter) -> T): T {
        val adapter = binding.recyclerBlocks.adapter as AdminTaskEditBlocksAdapter
        return adapter.let(action)
    }

    override fun observeModel() {

        model.loadingLiveData.observe(viewLifecycleOwner) {
            // TODO
        }

        model.addNewBlockLiveData.observe(viewLifecycleOwner) { data ->
            adapterAction {
                it.addItemAt(data.block, data.position)
            }
        }

        model.blocksLiveData.observeOnce(viewLifecycleOwner) { data ->
            adapterAction {
                it.setItems(data)
            }
        }

        model.taskNameLiveData.observeOnce(viewLifecycleOwner) { name ->
            binding.nameEditText.setText(name)
        }

        model.removeBlockAtPositionLiveData.observe(viewLifecycleOwner) { position ->
            adapterAction {
                it.removeItemAtPosition(position)
            }
        }

        model.openTestScreen.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }
}