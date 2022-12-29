package com.iish.pulse.domain.repository

import com.iish.pulse.data.source.request.*
import com.iish.pulse.data.source.request.commentRequests.CommentFilter
import com.iish.pulse.data.source.request.commentRequests.DeleteComment
import com.iish.pulse.data.source.request.commentRequests.NewComment
import com.iish.pulse.data.source.responses.*
import com.iish.pulse.data.source.responses.comment.Comment
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import java.util.*

interface ApiHelper {

    /**
     * Authorization
     */

    suspend fun subscription(
        token: String,
        previewModel: PreviewModel
    ): Response<UserSubscriptionResponse>

    suspend fun setLikePublication(
        token: String,
        likeTo: AttachTo
    ): Response<PublicationCounters>

    suspend fun setLikeOrganization(
        token: String,
        orgID: Entity
    ): Response<PublicationCounters>


    suspend fun getUserOrOrgPreview(
        token: String,
        previewModel: PreviewModel
    ): Response<PreviewUserOrOrganization>


    /**
     * Publication
     */
    suspend fun getUserPublications(
        token: String,
        inf: UserPubFilter
    ): Response<ArrayList<ResultPublicationsItem>>

    suspend fun getPublicationsUserOrOrganization(
        token: String?,
        inf: UserPubFilter,
        k: Int,
    ): Response<ArrayList<ResultPublicationsItem>>

    suspend fun getEventPublications(
        token: String?,
        inf: Entity,
        k: Int,
    ): Response<ArrayList<ResultPublicationsItem>>

    suspend fun getPublicationsMapObject(
        token: String?,
        inf: Entity,
        k: Int,
    ): Response<ArrayList<ResultPublicationsItem>>

    suspend fun getAllPublication(
        token: String?,
        k: Int,
        pubEntity: PubFilter?
    ): Response<ArrayList<ResultPublicationsItem>>

    suspend fun getInfoByPointId(
        token: String?,
        inf: List<InfoById>
    ): Response<Collection<ResultPublicationsItem>>

    /**
     * Localization Categories
     */
    fun getAllPublicationCategories(
        culture: String = Locale.getDefault().language
    ): Call<ArrayList<CategoriesResult>>

    fun getAllPublicationTypes(
        culture: String = Locale.getDefault().language
    ): Call<List<PublicationTypeResult>>


    /**
     * Add File
     */

    suspend fun addFile(
        token: String?,
    ): Response<List<Entity>>


    suspend fun createPost(
        token: String?,
        post: NewPublication
    )

    suspend fun createEvent(
        token: String?,
        event: NewPublication
    )

    suspend fun createMapObj(
        token: String?,
        mapObj: NewPublication
    )


    suspend fun createOrg(
        token: String?,
        newOrg: NewPublication
    )

    suspend fun createRoute(
        token: String?,
        newOrg: NewPublication
    )


    fun getAttachObjOnMap(
        accessToken: String,
        geoPosition: GeoHash,
        pre: Int = 4
    ): Call<ObjectOnMap>


    fun getGlobalObjOnMap(
        geoPosition: GeoHash,
        pre: Int = 4
    ): Call<GlobalObjectOnMap>


    fun getRegion(
        region: Region,
        culture: String = Locale.getDefault().language
    ): Call<ResultRegion>


    suspend fun getOrgSettings(
        token: String,
        profileId: Entity
    ): Response<OrganizationSettings>

    /**
     * Comments
     */


    suspend fun getPublicationComments(
        token: String?,
        k: Int,
        comment: CommentFilter
    ): Response<List<Comment>>


    suspend fun createComment(
        token: String?,
        newComment: NewComment
    ): Response<Comment>


    suspend fun removeComment(
        token: String?,
        deleteComment: DeleteComment
    )

}