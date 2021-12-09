package com.flaringapp.testingsimulator.admin.presentation.test.models

sealed interface AdminTestListItemViewData

data class AdminTestHeaderViewData(
    val name: CharSequence,
    val status: CharSequence,
    val statistics: CharSequence,
) : AdminTestListItemViewData

data class AdminTestBlockViewData(
    val id: Int,
    val text: CharSequence,
) : AdminTestListItemViewData

object AdminTestAddBlockViewData : AdminTestListItemViewData