package com.flaringapp.testingsimulator.admin.presentation.task_edit.models

data class AdminTaskEditBlockViewData(
    val id: Int,
    var text: CharSequence,
    var isBlockActive: Boolean,
    var isLinked: Boolean,
)