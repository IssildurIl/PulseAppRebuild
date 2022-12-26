package com.iish.pulse.data.source.responses

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("title")val title: String,
    @SerializedName("code")val code: Int,
    @SerializedName("detail")val detail: String
)