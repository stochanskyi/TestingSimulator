package com.flaringapp.testingsimulator.admin.domain.tasks.models

class AdminTaskBlock(
    val id: Int,
    val text: String,
    val isEnabled: Boolean,
    val linkedBlockId: Int?
)