package com.flaringapp.testingsimulator.admin.presentation.task_edit.adapter

interface AdminTaskEditBlocksTouchHelperAdapter {

    fun canDrag(position: Int): Boolean

    fun canMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemMove(fromPosition: Int, toPosition: Int)

}