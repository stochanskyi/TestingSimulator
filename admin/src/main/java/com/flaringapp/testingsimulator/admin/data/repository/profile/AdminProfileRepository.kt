package com.flaringapp.testingsimulator.admin.data.repository.profile

import com.flaringapp.testingsimulator.admin.data.storage.profile.AdminProfileDataStorage
import com.flaringapp.testingsimulator.admin.domain.profile.AdminProfile
import com.flaringapp.testingsimulator.admin.domain.profile.EditAdminProfile
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.data.network.features.edit_profile.EditProfileDataSource
import com.flaringapp.testingsimulator.data.network.features.edit_profile.request.EditProfileRequest
import com.flaringapp.testingsimulator.domain.storage.DataStorage

interface AdminProfileRepository {

    suspend fun getProfile(): CallResult<AdminProfile>

    suspend fun saveProfile(profile: AdminProfile)

    suspend fun editProfile(profile: EditAdminProfile): CallResult<AdminProfile>

    fun isLoggedIn(): Boolean

    suspend fun getLastEmail(): CallResult<String>

    suspend fun logout(): CallResultNothing

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
            .doOnSuccess { saveIntoStorage(it) }
    }

    override suspend fun getLastEmail(): CallResult<String> {
        val email = profileDataStorage.email ?: ""
        return CallResult.Success(email)
    }

    override fun isLoggedIn(): Boolean {
        return dataStorage.token != null && dataStorage.userId != -1
    }

    override suspend fun logout(): CallResultNothing {
        clearStorage()
        return CallResult.Success(Unit)
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

    private fun clearStorage() {
        dataStorage.userId = -1
        with(profileDataStorage) {
            firstName = null
            lastName = null
            workPlace = null
            role = null
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