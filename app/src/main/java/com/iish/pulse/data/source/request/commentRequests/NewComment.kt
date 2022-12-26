package com.iish.pulse.data.source.request.commentRequests

import com.google.gson.annotations.SerializedName

data class NewComment(
    @SerializedName("description") val description: String?,
    @SerializedName("publicationId") val publicationId: String?,
    @SerializedName("publicationTypeId") val publicationTypeId: String?,
    @SerializedName("userId") val userId: String?
)