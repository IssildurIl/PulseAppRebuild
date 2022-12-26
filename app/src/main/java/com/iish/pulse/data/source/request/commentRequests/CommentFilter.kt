package com.iish.pulse.data.source.request.commentRequests

import com.google.gson.annotations.SerializedName

data class CommentFilter(
    @SerializedName("id")
    val id: String?,
    @SerializedName("lastId")
    var lastId: String?,
){
    constructor(id: String) : this(
        id,
        lastId = null
    )
}