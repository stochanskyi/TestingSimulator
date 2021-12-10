package com.flaringapp.testingsimulator.data.network.features.edit_profile

import com.flaringapp.testingsimulator.core.data.network.base.ApiResponse
import com.flaringapp.testingsimulator.data.network.features.edit_profile.request.EditProfileRequest
import com.flaringapp.testingsimulator.data.network.features.edit_profile.response.EditProfileResponse
import retrofit2.http.Body
import retrofit2.http.PUT

interface EditProfileApi {

    @PUT("User")
    suspend fun editProfile(
        @Body request: EditProfileRequest
    ) : ApiResponse<EditProfileResponse>

}