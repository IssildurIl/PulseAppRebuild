package com.iish.pulse.data.source.request.commentRequests

import com.google.gson.annotations.SerializedName

data class DeleteComment(
    @SerializedName("id")
    val id: String
)