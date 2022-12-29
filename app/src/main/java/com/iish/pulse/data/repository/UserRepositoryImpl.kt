package com.iish.pulse.data.repository

import com.iish.pulse.data.remote.Api
import com.iish.pulse.data.source.request.AuthUser
import com.iish.pulse.data.source.request.CreateUser
import com.iish.pulse.data.source.request.Entity
import com.iish.pulse.data.source.request.UserChangeData
import com.iish.pulse.data.source.responses.*
import com.iish.pulse.domain.repository.UserHelper
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body

class UserRepositoryImpl(private val api: Api) : UserHelper {

    override suspend fun authUser(@Body auth: AuthUser): Response<UserAuth> =
        api.authUser(auth)

    override suspend fun getNewToken(@Body auth: AuthUser): Call<UserAuth> =
        api.getNewToken(auth)

    override suspend fun createNewUser(createUser: CreateUser): Response<NewUser> =
        api.createNewUser(createUser)

    override suspend fun activatedUser(code: String): Response<NewUser> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserInf(token: String, revision: Int): Response<UserInfo> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserInfForEdit(token: String): Response<UserEditModel> {
        TODO("Not yet implemented")
    }

    override suspend fun changeUserData(token: String, changeData: UserChangeData): Response<UserChangeResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun setUserAvatar(token: String?, uri: MultipartBody.Part): Response<Entity> {
        TODO("Not yet implemented")
    }
}