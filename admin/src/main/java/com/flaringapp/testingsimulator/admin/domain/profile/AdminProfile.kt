package com.flaringapp.testingsimulator.admin.domain.profile

import com.flaringapp.testingsimulator.domain.features.profile.Profile

class AdminProfile(
    override val id: String,
    override val firstName: String,
    override val lastName: String,
    override val email: String,
    val workPlace: String?,
    val role: String?,
) : Profile