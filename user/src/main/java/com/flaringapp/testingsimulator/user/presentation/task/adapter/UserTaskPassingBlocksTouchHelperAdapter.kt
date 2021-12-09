package com.flaringapp.testingsimulator.user.presentation.task.adapter

interface UserTaskPassingBlocksTouchHelperAdapter {

    fun canDrag(position: Int): Boolean

    fun canMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemMove(fromPosition: Int, toPosition: Int)

}