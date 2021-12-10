package com.flaringapp.testingsimulator.user.presentation.task.models

data class UserTaskPassingBlockViewData(
    val id: Int,
    val text: CharSequence,
    var isBlockActive: Boolean,
)