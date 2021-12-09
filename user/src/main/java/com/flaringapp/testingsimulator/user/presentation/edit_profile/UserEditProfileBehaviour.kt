package com.flaringapp.testingsimulator.user.presentation.edit_profile

import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.domain.features.profile.EditProfileUseCase
import com.flaringapp.testingsimulator.presentation.features.edit_profile.EditProfileBehaviour
import com.flaringapp.testingsimulator.user.domain.profile.EditUserProfile
import com.flaringapp.testingsimulator.user.domain.profile.UserProfile

class UserEditProfileBehaviour(
    private val editProfileUseCase: EditProfileUseCase<EditUserProfile, UserProfile>
) : EditProfileBehaviour {

    override val isStudyingEnabled: Boolean
        get() = true
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
        val editProfile = EditUserProfile(
            firstName = firstName,
            lastName = lastName,
            studying = studying,
            workPlace = workPlace,
            role = role
        )
        return editProfileUseCase(editProfile).ignoreData()
    }
}