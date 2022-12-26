package com.iish.pulse.data.remote

import com.iish.pulse.data.source.request.*
import com.iish.pulse.data.source.request.commentRequests.*
import com.iish.pulse.data.source.responses.*
import com.iish.pulse.data.source.responses.comment.Comment
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface Api {

    /**
     * Authorization
     */
    @Headers("Content-Type: application/json", "Accept:application/json")
    @POST("auth")
    suspend fun authUser(@Body auth: AuthUser): Response<UserAuth>

    @Headers("Content-Type: application/json", "Accept:application/json")
    @POST("auth")
    fun getNewToken(@Body auth: AuthUser): Call<UserAuth>

    @POST("subscriptions/add")
    suspend fun subscription(
        @Header("Authorization")
        token: String,
        @Body previewModel: PreviewModel
    ): Response<UserSubscriptionResponse>

    @POST("publications/like")
    suspend fun setLikePublication(
        @Header("Authorization")
        token: String,
        @Body likeTo: AttachTo
    ): Response<PublicationCounters>

    @POST("publications/like/organization")
    suspend fun setLikeOrganization(
        @Header("Authorization")
        token: String,
        @Body orgID: Entity
    ): Response<PublicationCounters>

    /**
     * Registration new user
     */
    @Headers("Content-Type: application/json", "Accept:application/json")
    @POST("users/create")
    suspend fun createNewUser(
        @Body createUser: CreateUser
    ): Response<NewUser>

    /**
     * Activating new user
     */
    @Headers("Content-Type: application/json", "Accept:application/json")
    @GET("auth/activated")
    suspend fun activatedUser(
        @Query("code")
        code: String
    ): Response<NewUser>

    /**
     * Get user information by token
     */
    @Headers("Content-Type: application/json", "Accept:application/json")
    @GET("users/info")
    suspend fun getUserInf(
        @Header("Authorization")
        token: String,
        @Query("revision")
        revision: Int
    ): Response<UserInfo>

    @Headers("Content-Type: application/json", "Accept:application/json")
    @POST("users/preview")
    suspend fun getUserOrOrgPreview(
        @Header("Authorization")
        token: String,
        @Body
        previewModel: PreviewModel
    ): Response<PreviewUserOrOrganization>

    @Headers("Content-Type: application/json", "Accept:application/json")
    @GET("users")
    suspend fun getUserInfForEdit(
        @Header("Authorization")
        token: String
    ): Response<UserEditModel>

    @Headers("Content-Type: application/json", "Accept:application/json")
    @POST("users/change")
    suspend fun changeUserData(
        @Header("Authorization")
        token: String,
        @Body
        changeData: UserChangeData
    ): Response<UserChangeResponse>

    @Multipart
    @POST("files/set_avatar")
    suspend fun setUserAvatar(
        @Header("Authorization")
        token: String?,
        @Part uri: MultipartBody.Part
    ): Response<Entity>

    /**
     * Publication
     */
    @Headers("Content-Type: application/json", "Accept:application/json")
    @POST("publications/user/0")
    suspend fun getUserPublications(
        @Header("Authorization")
        token: String,
        @Body
        inf: UserPubFilter
    ): Response<ArrayList<ResultPublicationsItem>>

    @POST("publications/user/{k}")
    suspend fun getPublicationsUserOrOrganization(
        @Header("Authorization")
        token: String?,
        @Body
        inf: UserPubFilter,
        @Path("k")
        k: Int,
    ): Response<ArrayList<ResultPublicationsItem>>

    @POST("publications/user/{k}")
    suspend fun getEventPublications(
        @Header("Authorization")
        token: String?,
        @Body
        inf: Entity,
        @Path("k")
        k: Int,
    ): Response<ArrayList<ResultPublicationsItem>>

    @POST("mapobject/publications/{k}")
    suspend fun getPublicationsMapObject(
        @Header("Authorization")
        token: String?,
        @Body
        inf: Entity,
        @Path("k")
        k: Int,
    ): Response<ArrayList<ResultPublicationsItem>>

    @POST("publications/{k}")
    suspend fun getAllPublication(
        @Header("Authorization")
        token: String?,
        @Path("k")
        k: Int,
        @Body
        pubEntity: PubFilter?
    ): Response<ArrayList<ResultPublicationsItem>>

    @POST("publications/map")
    suspend fun getInfoByPointId(
        @Header("Authorization")
        token: String?,
        @Body
        inf: List<InfoById>
    ): Response<Collection<ResultPublicationsItem>>

    /**
     * Localization Categories
     */
    @GET("resource/categories")
    fun getAllPublicationCategories(
        @Header("Accept-Language")
        culture: String = Locale.getDefault().language
    ): Call<ArrayList<CategoriesResult>>

    @GET("resource/types")
    fun getAllPublicationTypes(
        @Header("Accept-Language")
        culture: String = Locale.getDefault().language
    ): Call<List<PublicationTypeResult>>


    /**
     * Add File
     */
    @POST("files/images/create")
    suspend fun addFile(
        @Header("Authorization")
        token: String?,
        @Body uri: RequestBody
    ): Response<List<Entity>>


    @POST("publications/posts/create")
    suspend fun createPost(
        @Header("Authorization")
        token: String?,
        @Body
        post: NewPublication
    )

    @POST("publications/events/create")
    suspend fun createEvent(
        @Header("Authorization")
        token: String?,
        @Body
        event: NewPublication
    )

    @POST("mapObject/create")
    suspend fun createMapObj(
        @Header("Authorization")
        token: String?,
        @Body
        mapObj: NewPublication
    )

    @POST("organizations/create")
    suspend fun createOrg(
        @Header("Authorization")
        token: String?,
        @Body
        newOrg: NewPublication
    )

    @POST("publications/routes/create")
    suspend fun createRoute(
        @Header("Authorization")
        token: String?,
        @Body
        newOrg: NewPublication
    )

    @POST("publications/near")
    fun getAttachObjOnMap(
        @Header("Authorization")
        accessToken: String,
        @Body
        geoPosition: GeoHash,
        @Query("pre")
        pre: Int = 4
    ): Call<ObjectOnMap>

    @POST("publications/map/points")
    fun getGlobalObjOnMap(
        @Body
        geoPosition: GeoHash,
        @Query("pre")
        pre: Int = 4
    ): Call<GlobalObjectOnMap>


    @POST("region")
    fun getRegion(
        @Body
        region: Region,
        @Query("culture")
        culture: String = Locale.getDefault().language
    ): Call<ResultRegion>

    @POST("organizations/settings")
    suspend fun getOrgSettings(
        @Header("Authorization")
        token: String,
        @Body
        profileId: Entity
    ): Response<OrganizationSettings>

    /**
     * Comments
     */

    @POST("comments/{k}")
    suspend fun getPublicationComments(
        @Header("Authorization")
        token: String?,
        @Path("k")
        k: Int,
        @Body
        comment: CommentFilter
    ): Response<List<Comment>>

    @POST("comments/create")
    suspend fun createComment(
        @Header("Authorization")
        token: String?,
        @Body
        newComment: NewComment
    ): Response<Comment>

    @POST("comments/remove")
    suspend fun removeComment(
        @Header("Authorization")
        token: String?,
        @Body
        deleteComment: DeleteComment
    )


}