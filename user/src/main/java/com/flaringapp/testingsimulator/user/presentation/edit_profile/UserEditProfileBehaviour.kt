package com.flaringapp.testingsimulator.user.presentation.edit_profile

import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.domain.features.profile.EditProfileUseCase
import com.flaringapp.testingsimulator.domain.features.profile.GetProfileUseCase
import com.flaringapp.testingsimulator.presentation.features.edit_profile.behaviour.EditProfileBehaviour
import com.flaringapp.testingsimulator.presentation.features.edit_profile.behaviour.EditProfileBehaviourGetProfileConsumer
import com.flaringapp.testingsimulator.user.domain.profile.EditUserProfile
import com.flaringapp.testingsimulator.user.domain.profile.UserProfile
import kotlinx.coroutines.Dispatchers

class UserEditProfileBehaviour(
    private val getProfileUseCase: GetProfileUseCase<UserProfile>,
    private val editProfileUseCase: EditProfileUseCase<EditUserProfile, UserProfile>,
) : EditProfileBehaviour {

    override val isStudyingEnabled: Boolean
        get() = true
    override val isWorkPlaceEnabled: Boolean
        get() = true
    override val isRoleEnabled: Boolean
        get() = true

    override suspend fun loadProfile(
        consumer: EditProfileBehaviourGetProfileConsumer
    ): CallResultNothing {
        return getProfileUseCase()
            .doOnSuccessSuspend(Dispatchers.Main) {
                processProfileData(consumer, it)
            }
            .ignoreData()
    }

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

    private fun processProfileData(
        consumer: EditProfileBehaviourGetProfileConsumer,
        profile: UserProfile,
    ) {
        consumer.handleProfileData(
            firstName = profile.firstName,
            lastName = profile.lastName,
            studying = profile.studying,
            workPlace = profile.workPlace,
            role = profile.role
        )
    }
}