package com.flaringapp.testingsimulator.user.presentation.task.adapter

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.core.presentation.utils.inflater
import com.flaringapp.testingsimulator.user.databinding.ViewHolderUserTaskPassingBlockBinding
import com.flaringapp.testingsimulator.user.presentation.task.models.UserTaskPassingBlockViewData

@SuppressLint("ClickableViewAccessibility")
class UserTaskPassingBlockViewHolder private constructor(
    private val binding: ViewHolderUserTaskPassingBlockBinding
): RecyclerView.ViewHolder(binding.root), UserTaskPassingBlockDraggableViewHolder {

    companion object {
        fun create(parent: ViewGroup) = UserTaskPassingBlockViewHolder(
            ViewHolderUserTaskPassingBlockBinding.inflate(parent.inflater, parent, false)
        )
    }

    override fun onDragStarted() {
        // TODO animation
    }

    override fun onDragEnded() {
        // TODO animation
    }

    fun bind(
        dragListener: UserTaskPassingBlockTouchDragListener,
        item: UserTaskPassingBlockViewData,
        onEnabledChanged: (Int, Boolean) -> Unit,
    ) = with(binding) {
        textBlock.text = item.text

        layoutBlockEnabled.setOnClickListener {
            checkboxBlockEnabled.toggle()
        }

        checkboxBlockEnabled.setChecked(item.isEnabled, false)
        checkboxBlockEnabled.setOnCheckedChangeListener { _, isChecked ->
            onEnabledChanged(item.id, isChecked)
        }

        buttonDrag.setOnTouchListener(DragTouchListener(dragListener))
    }

    fun release() = with(binding) {
        layoutBlockEnabled.setOnClickListener(null)
        checkboxBlockEnabled.setOnCheckedChangeListener(null)
        buttonDrag.setOnTouchListener(null)
    }

    private fun beginDrag(dragListener: UserTaskPassingBlockTouchDragListener): Boolean {
        return dragListener.beginTouchDrag(this)
    }

    private inner class DragTouchListener(
        private val dragListener: UserTaskPassingBlockTouchDragListener
    ): View.OnTouchListener {
        override fun onTouch(v: View?, event: MotionEvent): Boolean {
            if (event.action != MotionEvent.ACTION_DOWN) return false
            beginDrag(dragListener)
            return true
        }
    }

}