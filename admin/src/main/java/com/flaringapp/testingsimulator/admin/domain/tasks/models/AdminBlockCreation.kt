package com.flaringapp.testingsimulator.admin.domain.tasks.models

class AdminBlockCreation(
    val name: String,
    val isEnabled: Boolean,
    val linkedBlockId: Int?
)