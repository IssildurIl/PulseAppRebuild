package com.iish.pulse.data.source.request


import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("ResultType")
    val resultType: String,
    @SerializedName("SearchArea")
    val searchArea: String
)