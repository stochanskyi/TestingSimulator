package com.flaringapp.testingsimulator.user.presentation.task.adapter

import androidx.recyclerview.widget.RecyclerView

interface UserTaskPassingBlockTouchDragListener {

    fun beginTouchDrag(holder: RecyclerView.ViewHolder): Boolean

}