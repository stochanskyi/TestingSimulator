package com.flaringapp.testingsimulator.user.data.repository.profile

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.data.network.features.edit_profile.EditProfileDataSource
import com.flaringapp.testingsimulator.data.network.features.edit_profile.request.EditProfileRequest
import com.flaringapp.testingsimulator.domain.storage.DataStorage
import com.flaringapp.testingsimulator.user.data.storage.profile.UserProfileDataStorage
import com.flaringapp.testingsimulator.user.domain.profile.EditUserProfile
import com.flaringapp.testingsimulator.user.domain.profile.UserProfile

interface UserProfileRepository {

    suspend fun getProfile(): CallResult<UserProfile>

    suspend fun saveProfile(profile: UserProfile)

    suspend fun editProfile(profile: EditUserProfile): CallResult<UserProfile>

    suspend fun logout(): CallResultNothing

}

class UserProfileRepositoryImpl(
    private val dataStorage: DataStorage,
    private val profileDataStorage: UserProfileDataStorage,
    private val editProfileDataSource: EditProfileDataSource,
    private val editProfileMapper: UserEditProfileMapper,
) : UserProfileRepository {

    override suspend fun getProfile(): CallResult<UserProfile> {
        val profile = createProfileFromStorage()
            ?: return CallResult.Error("Error loading user profile")

        return CallResult.Success(profile)
    }

    override suspend fun saveProfile(profile: UserProfile) {
        saveIntoStorage(profile)
    }

    override suspend fun editProfile(profile: EditUserProfile): CallResult<UserProfile> {
        val request = profile.createEditProfileRequest()
        return editProfileDataSource.editProfile(request)
            .transform { editProfileMapper.map(this) }
            .doOnSuccess { saveIntoStorage(it) }
    }

    override suspend fun logout(): CallResultNothing {
        clearStorage()
        return CallResult.Success(Unit)
    }

    private fun createProfileFromStorage(): UserProfile? {
        return UserProfile(
            id = dataStorage.userId,
            firstName = profileDataStorage.firstName ?: return null,
            lastName = profileDataStorage.lastName ?: return null,
            email = profileDataStorage.email ?: return null,
            studying = profileDataStorage.studying,
            workPlace = profileDataStorage.workPlace,
            role = profileDataStorage.role,
        )
    }

    private fun saveIntoStorage(profile: UserProfile) {
        dataStorage.userId = profile.id
        with(profileDataStorage) {
            firstName = profile.firstName
            lastName = profile.lastName
            email = profile.email
            studying = profile.studying
            workPlace = profile.workPlace
            role = profile.role
        }
    }

    private fun clearStorage() {
        dataStorage.userId = -1
        with(profileDataStorage) {
            firstName = null
            lastName = null
            email = null
            studying = null
            workPlace = null
            role = null
        }
    }

    private fun EditUserProfile.createEditProfileRequest(): EditProfileRequest {
        return EditProfileRequest(
            firstName = firstName,
            lastName = lastName,
            studying = studying,
            workPlace = workPlace,
            role = role
        )
    }
}