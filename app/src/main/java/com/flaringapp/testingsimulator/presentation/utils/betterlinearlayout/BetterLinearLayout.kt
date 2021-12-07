package com.flaringapp.testingsimulator.presentation.utils.betterlinearlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.res.use
import androidx.core.view.children
import com.flaringapp.testingsimulator.R

/**
 * Features:
 * - Children spacing
 */
open class BetterLinearLayout : LinearLayout {

    var childrenSpacing: Int = 0
        set(value) {
            field = value / 2
            requestLayout()
        }

    private val originalChildrenMargins: MutableMap<Int, Pair<Int, Int>> = mutableMapOf()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 9)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs, defStyleAttr, defStyleRes)
    }

    private fun init(
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
    ) {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.BetterLinearLayout,
            defStyleAttr,
            defStyleRes
        ).use {
            childrenSpacing = it.getDimensionPixelSize(
                R.styleable.BetterLinearLayout_bll_children_spacing,
                0
            )
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (childrenSpacing > 0) {
            applySpacingOnMeasure()
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun applySpacingOnMeasure() {
        val visibleChildren = children.filter { it.visibility != View.GONE }.toList()
        val visibleChildrenSize = visibleChildren.size
        if (visibleChildrenSize <= 1) return

        val spacer =
            if (orientation == HORIZONTAL) HorizontalMarginSpacer
            else VerticalMarginSpacer

        val lastIndex = visibleChildrenSize - 1

        visibleChildren.forEachIndexed { index, view ->
            applyMargin(view, index == 0, index == lastIndex, spacer)
        }
    }

    private fun applyMargin(view: View, isFirst: Boolean, isLast: Boolean, spacer: MarginSpacer) {
        val params = view.layoutParams as LayoutParams

        val originalMargins = resolveOriginalMargin(view.id, params, spacer)

        if (!isFirst) spacer.applyStartMargin(params, originalMargins.first + childrenSpacing)
        if (!isLast) spacer.applyEndMargin(params, originalMargins.first + childrenSpacing)
    }

    private fun resolveOriginalMargin(
        id: Int,
        params: LayoutParams,
        spacer: MarginSpacer
    ): Pair<Int, Int> {
        var margins = originalChildrenMargins[id]
        if (margins == null) {
            margins = spacer.resolveChildMargins(params)
            originalChildrenMargins[id] = margins
        }
        return margins
    }

    private abstract class MarginSpacer {

        abstract fun resolveChildMargins(layoutParams: LayoutParams): Pair<Int, Int>

        abstract fun applyStartMargin(params: LayoutParams, margin: Int)
        abstract fun applyEndMargin(params: LayoutParams, margin: Int)

    }

    private object HorizontalMarginSpacer : MarginSpacer() {
        override fun resolveChildMargins(layoutParams: LayoutParams): Pair<Int, Int> {
            return layoutParams.leftMargin to layoutParams.rightMargin
        }

        override fun applyStartMargin(params: LayoutParams, margin: Int) {
            params.leftMargin = margin
        }

        override fun applyEndMargin(params: LayoutParams, margin: Int) {
            params.rightMargin = margin
        }
    }

    private object VerticalMarginSpacer : MarginSpacer() {
        override fun resolveChildMargins(layoutParams: LayoutParams): Pair<Int, Int> {
            return layoutParams.topMargin to layoutParams.bottomMargin
        }

        override fun applyStartMargin(params: LayoutParams, margin: Int) {
            params.topMargin = margin
        }

        override fun applyEndMargin(params: LayoutParams, margin: Int) {
            params.bottomMargin = margin
        }
    }


}