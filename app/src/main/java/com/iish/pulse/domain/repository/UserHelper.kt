package com.iish.pulse.domain.repository

import com.iish.pulse.data.source.request.AuthUser
import com.iish.pulse.data.source.request.CreateUser
import com.iish.pulse.data.source.request.Entity
import com.iish.pulse.data.source.request.UserChangeData
import com.iish.pulse.data.source.responses.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response

interface UserHelper {

    suspend fun authUser(auth: AuthUser): Response<UserAuth>

    suspend fun getNewToken(auth: AuthUser): Call<UserAuth>

    suspend fun createNewUser(
        createUser: CreateUser
    ): Response<NewUser>

    suspend fun activatedUser(
        code: String
    ): Response<NewUser>

    suspend fun getUserInf(
        token: String,
        revision: Int
    ): Response<UserInfo>

    suspend fun getUserInfForEdit(
        token: String
    ): Response<UserEditModel>


    suspend fun changeUserData(
        token: String,
        changeData: UserChangeData
    ): Response<UserChangeResponse>

    suspend fun setUserAvatar(
        token: String?,
        uri: MultipartBody.Part
    ): Response<Entity>

}