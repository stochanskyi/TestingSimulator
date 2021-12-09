package com.flaringapp.testingsimulator.admin.presentation.test.models

sealed interface AdminTestListItemViewData

data class AdminTestHeaderViewData(
    val name: CharSequence,
    val statusAndStatistics: CharSequence,
) : AdminTestListItemViewData

data class AdminTestTaskViewData(
    val id: Int,
    val text: CharSequence,
) : AdminTestListItemViewData

object AdminTestAddTaskViewData : AdminTestListItemViewData