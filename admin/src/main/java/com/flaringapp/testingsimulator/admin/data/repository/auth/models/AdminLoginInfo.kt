package com.flaringapp.testingsimulator.admin.data.repository.auth.models

import com.flaringapp.testingsimulator.admin.domain.profile.AdminProfile

class AdminLoginInfo(
    val token: String,
    val profile: AdminProfile
)