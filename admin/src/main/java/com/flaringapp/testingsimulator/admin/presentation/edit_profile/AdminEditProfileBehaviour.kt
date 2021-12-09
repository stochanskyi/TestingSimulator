package com.flaringapp.testingsimulator.admin.presentation.edit_profile

import com.flaringapp.testingsimulator.admin.domain.profile.AdminProfile
import com.flaringapp.testingsimulator.admin.domain.profile.EditAdminProfile
import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.domain.features.profile.EditProfileUseCase
import com.flaringapp.testingsimulator.presentation.features.edit_profile.EditProfileBehaviour

class AdminEditProfileBehaviour(
    private val editProfileUseCase: EditProfileUseCase<EditAdminProfile, AdminProfile>
) : EditProfileBehaviour {

    override val isWorkPlaceEnabled: Boolean
        get() = true
    override val isRoleEnabled: Boolean
        get() = true

    override suspend fun editProfile(
        firstName: String,
        lastName: String,
        studying: String,
        workPlace: String,
        role: String
    ): CallResultNothing {
        val editProfile = EditAdminProfile(
            firstName = firstName,
            lastName = lastName,
            workPlace = workPlace,
            role = role
        )
        return editProfileUseCase(editProfile).ignoreData()
    }
}