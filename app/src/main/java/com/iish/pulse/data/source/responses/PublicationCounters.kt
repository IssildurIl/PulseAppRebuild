package com.iish.pulse.data.source.responses


import com.google.gson.annotations.SerializedName

data class PublicationCounters(
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("publications")
    val publications: Int,
    @SerializedName("pulse")
    val pulse: Int,
    @SerializedName("views")
    val views: Int?,
    @SerializedName("isLike")
    val isLike: Boolean,
    val id: String,
    val typeName: String
)