package com.iish.pulse.data.source.responses.commentsResponse

import com.google.gson.annotations.SerializedName


data class CommentResponse(
    @SerializedName("user")
    val commentedUserData: ResponseUserData,
    @SerializedName("subscription")
    val commentedUserPublication: ResponseUserPublication
)