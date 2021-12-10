package com.flaringapp.testingsimulator.admin.domain.tasks.models

data class AdminTaskBlock(
    val id: Int,
    val text: String,
    val isEnabled: Boolean,
    val linkedBlockId: Int?
)