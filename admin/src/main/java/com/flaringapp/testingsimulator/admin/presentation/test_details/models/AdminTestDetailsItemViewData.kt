package com.flaringapp.testingsimulator.admin.presentation.test_details.models

sealed interface AdminTestDetailsItemViewData

data class AdminTestDetailsHeaderViewData(
    val name: CharSequence,
    val statusAndStatistics: CharSequence,
) : AdminTestDetailsItemViewData

data class AdminTestDetailsTaskViewData(
    val id: Int,
    val text: CharSequence,
) : AdminTestDetailsItemViewData

object AdminTestDetailsAddTaskViewData : AdminTestDetailsItemViewData