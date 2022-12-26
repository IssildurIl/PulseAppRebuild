package com.iish.pulse.data.repository

import com.iish.pulse.data.remote.Api
import com.iish.pulse.data.source.request.*
import com.iish.pulse.data.source.request.commentRequests.CommentFilter
import com.iish.pulse.data.source.request.commentRequests.DeleteComment
import com.iish.pulse.data.source.request.commentRequests.NewComment
import com.iish.pulse.data.source.responses.*
import com.iish.pulse.data.source.responses.comment.Comment
import com.iish.pulse.domain.repository.ApiHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body

class RepositoryImpl(private val api: Api) : ApiHelper {

    override suspend fun authUser(@Body auth: AuthUser): Response<UserAuth> =
        api.authUser(auth)

    override suspend fun getNewToken(@Body auth: AuthUser): Call<UserAuth> =
        api.getNewToken(auth)

    override suspend fun subscription(token: String, previewModel: PreviewModel): Response<UserSubscriptionResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun setLikePublication(token: String, likeTo: AttachTo): Response<PublicationCounters> {
        TODO("Not yet implemented")
    }

    override suspend fun setLikeOrganization(token: String, orgID: Entity): Response<PublicationCounters> {
        TODO("Not yet implemented")
    }

    override suspend fun createNewUser(createUser: CreateUser): Response<NewUser> {
        TODO("Not yet implemented")
    }

    override suspend fun activatedUser(code: String): Response<NewUser> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserInf(token: String, revision: Int): Response<UserInfo> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserOrOrgPreview(token: String, previewModel: PreviewModel): Response<PreviewUserOrOrganization> {
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

    override suspend fun getUserPublications(token: String, inf: UserPubFilter): Response<ArrayList<ResultPublicationsItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPublicationsUserOrOrganization(token: String?, inf: UserPubFilter, k: Int): Response<ArrayList<ResultPublicationsItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getEventPublications(token: String?, inf: Entity, k: Int): Response<ArrayList<ResultPublicationsItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPublicationsMapObject(token: String?, inf: Entity, k: Int): Response<ArrayList<ResultPublicationsItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPublication(token: String?, k: Int, pubEntity: PubFilter?): Response<ArrayList<ResultPublicationsItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getInfoByPointId(token: String?, inf: List<InfoById>): Response<Collection<ResultPublicationsItem>> {
        TODO("Not yet implemented")
    }

    override fun getAllPublicationCategories(culture: String): Call<ArrayList<CategoriesResult>> {
        TODO("Not yet implemented")
    }

    override fun getAllPublicationTypes(culture: String): Call<List<PublicationTypeResult>> {
        TODO("Not yet implemented")
    }

    override suspend fun addFile(token: String?): Response<List<Entity>> {
        TODO("Not yet implemented")
    }

    override suspend fun createPost(token: String?, post: NewPublication) {
        TODO("Not yet implemented")
    }

    override suspend fun createEvent(token: String?, event: NewPublication) {
        TODO("Not yet implemented")
    }

    override suspend fun createMapObj(token: String?, mapObj: NewPublication) {
        TODO("Not yet implemented")
    }

    override suspend fun createOrg(token: String?, newOrg: NewPublication) {
        TODO("Not yet implemented")
    }

    override suspend fun createRoute(token: String?, newOrg: NewPublication) {
        TODO("Not yet implemented")
    }

    override fun getAttachObjOnMap(accessToken: String, geoPosition: GeoHash, pre: Int): Call<ObjectOnMap> {
        TODO("Not yet implemented")
    }

    override fun getGlobalObjOnMap(geoPosition: GeoHash, pre: Int): Call<GlobalObjectOnMap> {
        TODO("Not yet implemented")
    }

    override fun getRegion(region: Region, culture: String): Call<ResultRegion> {
        TODO("Not yet implemented")
    }

    override suspend fun getOrgSettings(token: String, profileId: Entity): Response<OrganizationSettings> {
        TODO("Not yet implemented")
    }

    override suspend fun getPublicationComments(token: String?, k: Int, comment: CommentFilter): Response<List<Comment>> {
        TODO("Not yet implemented")
    }

    override suspend fun createComment(token: String?, newComment: NewComment): Response<Comment> {
        TODO("Not yet implemented")
    }

    override suspend fun removeComment(token: String?, deleteComment: DeleteComment) {
        TODO("Not yet implemented")
    }
}