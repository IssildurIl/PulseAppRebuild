package com.iish.pulse.data.source.responses


import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("count")
    var count: Int,
    @SerializedName("map")
    val map: Int
)