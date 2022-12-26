package com.iish.pulse.data.source.request


import com.google.gson.annotations.SerializedName

data class PreviewModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("isUser")
    val isUser: Boolean?
)