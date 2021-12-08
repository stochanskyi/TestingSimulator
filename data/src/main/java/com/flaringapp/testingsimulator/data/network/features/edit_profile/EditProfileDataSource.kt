package com.flaringapp.testingsimulator.data.network.features.edit_profile

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.network.base.validate
import com.flaringapp.testingsimulator.data.network.features.edit_profile.request.EditProfileRequest
import com.flaringapp.testingsimulator.data.network.features.edit_profile.response.EditProfileResponse

interface EditProfileDataSource {

    suspend fun editProfile(request: EditProfileRequest): CallResult<EditProfileResponse>

}

class EditProfileDataSourceImpl(
    private val api: EditProfileApi
) : EditProfileDataSource {

    override suspend fun editProfile(
        request: EditProfileRequest
    ): CallResult<EditProfileResponse> {
        return api.editProfile(request).validate()
    }
}