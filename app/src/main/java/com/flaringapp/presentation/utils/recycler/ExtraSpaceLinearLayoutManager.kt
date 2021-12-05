package com.flaringapp.presentation.utils.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.presentation.utils.dpi

class ExtraSpaceLinearLayoutManager : LinearLayoutManager {

    companion object {
        fun create(context: Context, startSpace: Int, endSpace: Int) =
            ExtraSpaceLinearLayoutManager(context).apply {
                this.startSpace = startSpace
                this.endSpace = endSpace
            }

        fun createDpi(context: Context, startSpace: Int, endSpace: Int) = create(
            context, context.dpi(startSpace), context.dpi(endSpace)
        )

        fun create(context: Context, space: Int) = create(context, space, space)

        fun createDpi(context: Context, space: Int) = createDpi(context, space, space)
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    var startSpace = 0
    var endSpace = 0

    override fun calculateExtraLayoutSpace(state: RecyclerView.State, extraLayoutSpace: IntArray) {
        super.calculateExtraLayoutSpace(state, extraLayoutSpace)
        extraLayoutSpace[0] += startSpace
        extraLayoutSpace[1] += endSpace
    }

}