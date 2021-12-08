package com.flaringapp.testingsimulator.user.data.repository.auth.models

import com.flaringapp.testingsimulator.user.domain.profile.UserProfile

class UserLoginInfo(
    val token: String,
    val profile: UserProfile,
)