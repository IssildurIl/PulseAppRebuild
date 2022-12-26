package com.iish.pulse.data.source.responses.commentsResponse

import com.google.gson.annotations.SerializedName

class ResponseUserData(
    @SerializedName("id")
    val id: String,
    @SerializedName("publicName")
    val publicName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("data")
    val data: String
)