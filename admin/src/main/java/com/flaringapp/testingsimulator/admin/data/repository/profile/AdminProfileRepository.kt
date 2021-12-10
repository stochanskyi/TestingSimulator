package com.flaringapp.testingsimulator.admin.data.repository.profile

import com.flaringapp.testingsimulator.admin.data.storage.profile.AdminProfileDataStorage
import com.flaringapp.testingsimulator.admin.domain.profile.AdminProfile
import com.flaringapp.testingsimulator.admin.domain.profile.EditAdminProfile
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.data.network.features.edit_profile.EditProfileDataSource
import com.flaringapp.testingsimulator.data.network.features.edit_profile.request.EditProfileRequest
import com.flaringapp.testingsimulator.domain.storage.DataStorage

interface AdminProfileRepository {

    suspend fun getProfile(): CallResult<AdminProfile>

    suspend fun saveProfile(profile: AdminProfile)

    suspend fun editProfile(profile: EditAdminProfile): CallResult<AdminProfile>

}

class AdminProfileRepositoryImpl(
    private val dataStorage: DataStorage,
    private val profileDataStorage: AdminProfileDataStorage,
    private val editProfileDataSource: EditProfileDataSource,
    private val editProfileMapper: AdminEditProfileMapper,
) : AdminProfileRepository {

    override suspend fun getProfile(): CallResult<AdminProfile> {
        val profile = createProfileFromStorage()
            ?: return CallResult.Error("Error loading admin profile")

        return CallResult.Success(profile)
    }

    override suspend fun saveProfile(profile: AdminProfile) {
        saveIntoStorage(profile)
    }

    override suspend fun editProfile(profile: EditAdminProfile): CallResult<AdminProfile> {
        val request = profile.createEditProfileRequest()
        return editProfileDataSource.editProfile(request)
            .transform { editProfileMapper.map(this) }
    }

    private fun createProfileFromStorage(): AdminProfile? {
        return AdminProfile(
            id = dataStorage.userId,
            firstName = profileDataStorage.firstName ?: return null,
            lastName = profileDataStorage.lastName ?: return null,
            email = profileDataStorage.email ?: return null,
            workPlace = profileDataStorage.workPlace,
            role = profileDataStorage.role,
        )
    }

    private fun saveIntoStorage(profile: AdminProfile) {
        dataStorage.userId = profile.id
        with(profileDataStorage) {
            firstName = profile.firstName
            lastName = profile.lastName
            email = profile.email
            workPlace = profile.workPlace
            role = profile.role
        }
    }

    private fun EditAdminProfile.createEditProfileRequest(): EditProfileRequest {
        return EditProfileRequest(
            firstName = firstName,
            lastName = lastName,
            workPlace = workPlace,
            role = role
        )
    }
}