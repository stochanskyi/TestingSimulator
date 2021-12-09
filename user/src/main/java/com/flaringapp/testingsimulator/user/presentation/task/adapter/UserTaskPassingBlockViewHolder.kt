package com.flaringapp.testingsimulator.user.presentation.task.adapter

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.core.presentation.utils.dp
import com.flaringapp.testingsimulator.core.presentation.utils.inflater
import com.flaringapp.testingsimulator.user.databinding.ViewHolderUserTaskPassingBlockBinding
import com.flaringapp.testingsimulator.user.presentation.task.models.UserTaskPassingBlockViewData

@SuppressLint("ClickableViewAccessibility")
class UserTaskPassingBlockViewHolder private constructor(
    private val binding: ViewHolderUserTaskPassingBlockBinding
): RecyclerView.ViewHolder(binding.root), UserTaskPassingBlockDraggableViewHolder {

    companion object {
        private const val DRAG_ANIMATION_DURATION = 150L
        private const val ELEVATION_DELTA = 4

        fun create(parent: ViewGroup) = UserTaskPassingBlockViewHolder(
            ViewHolderUserTaskPassingBlockBinding.inflate(parent.inflater, parent, false)
        )
    }

    private var defaultElevation = 0f
    private var elevationAnimator: ValueAnimator? = null

    override fun onDragStarted() {
        defaultElevation = itemView.elevation
        itemView.animateElevation(defaultElevation + itemView.context.dp(ELEVATION_DELTA))
    }

    override fun onDragEnded() {
        itemView.animateElevation(defaultElevation)
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

        elevationAnimator?.cancel()
        elevationAnimator = null
    }

    private fun beginDrag(dragListener: UserTaskPassingBlockTouchDragListener): Boolean {
        return dragListener.beginTouchDrag(this)
    }

    private fun View.animateElevation(to: Float) {
        elevationAnimator?.cancel()
        elevationAnimator = ValueAnimator.ofFloat(elevation, to).apply {
            duration = DRAG_ANIMATION_DURATION
            interpolator = DecelerateInterpolator()
            addUpdateListener { elevation = it.animatedValue as Float }
            start()
        }
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