package com.iish.pulse.data.source.responses.commentsResponse

import com.google.gson.annotations.SerializedName

class ResponseUserPublication(
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("datePublication")
    val datePublication: String,
    @SerializedName("countLikes")
    val countLikes: String
)