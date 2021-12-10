package com.flaringapp.testingsimulator.admin.presentation.task_edit.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.flaringapp.testingsimulator.admin.databinding.ViewHolderAdminTaskEditBlockBinding
import com.flaringapp.testingsimulator.admin.presentation.task_edit.models.AdminTaskEditBlockViewData
import com.flaringapp.testingsimulator.core.presentation.utils.inflater
import com.flaringapp.testingsimulator.presentation.common.recycler.drag.DraggableViewHolder
import com.flaringapp.testingsimulator.presentation.common.recycler.drag.TouchDragListener

@SuppressLint("ClickableViewAccessibility")
class AdminTaskEditBlockViewHolder private constructor(
    private val binding: ViewHolderAdminTaskEditBlockBinding
): DraggableViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = AdminTaskEditBlockViewHolder(
            ViewHolderAdminTaskEditBlockBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(
        dragListener: TouchDragListener,
        item: AdminTaskEditBlockViewData,
        onTextChanged: (Int, String) -> Unit,
        onBlockActiveChanged: (Int, Boolean) -> Unit,
        onLinkClicked: (id: Int) -> Unit,
        onRemoveClicked: (id: Int) -> Unit,
    ) = with(binding) {
        buttonDelete.setOnClickListener {
            onRemoveClicked.invoke(item.id)
        }

        textBlock.setText(item.text)
        textBlock.doAfterTextChanged {
            onTextChanged(item.id, it.toString())
        }

        layoutBlockDisabled.setOnClickListener {
            checkboxBlockDisabled.toggle()
        }

        checkboxBlockDisabled.setChecked(!item.isBlockActive, false)
        checkboxBlockDisabled.setOnCheckedChangeListener { _, isChecked ->
            onBlockActiveChanged(item.id, !isChecked)
        }

        setIsLinked(item.isLinked)
        buttonLink.setOnClickListener {
            onLinkClicked(item.id)
        }

        buttonDrag.setOnTouchListener(DragTouchListener(dragListener))
    }

    override fun release() = with(binding) {
        super.release()
        layoutBlockDisabled.setOnClickListener(null)
        checkboxBlockDisabled.setOnCheckedChangeListener(null)
        buttonDrag.setOnTouchListener(null)
    }

    fun setIsLinked(isLinked: Boolean) {
        binding.buttonLink.isSelected = true
    }

}