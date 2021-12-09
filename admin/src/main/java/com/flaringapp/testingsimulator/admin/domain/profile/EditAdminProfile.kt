package com.flaringapp.testingsimulator.admin.domain.profile

import com.flaringapp.testingsimulator.domain.features.profile.EditProfile

class EditAdminProfile(
    override val firstName: String,
    override val lastName: String,
    val workPlace: String?,
    val role: String?,
) : EditProfile