package com.flaringapp.testingsimulator.presentation.common.recycler.drag

import androidx.recyclerview.widget.RecyclerView

interface TouchDragListener {

    fun beginTouchDrag(holder: RecyclerView.ViewHolder): Boolean

}