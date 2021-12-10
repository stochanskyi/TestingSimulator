package com.flaringapp.testingsimulator.presentation.common.recycler.drag

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.core.presentation.utils.dp

abstract class DraggableViewHolder(view: View) : RecyclerView.ViewHolder(view),
    DraggableViewHolderContract {

    companion object {
        const val DRAG_ANIMATION_DURATION = 150L
        const val ELEVATION_DELTA = 4
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

    @CallSuper
    protected open fun release() {
        elevationAnimator?.cancel()
        elevationAnimator = null
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

    private fun beginDrag(dragListener: TouchDragListener): Boolean {
        return dragListener.beginTouchDrag(this)
    }

    protected inner class DragTouchListener(
        private val dragListener: TouchDragListener
    ): View.OnTouchListener {

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouch(v: View?, event: MotionEvent): Boolean {
            if (event.action != MotionEvent.ACTION_DOWN) return false
            return beginDrag(dragListener)
        }
    }
}