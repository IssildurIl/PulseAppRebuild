package com.iish.pulse.data.source.request

import com.google.gson.annotations.SerializedName

data class GeoHash(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("types")
    val type: ArrayList<String>? = null,
    @SerializedName("category")
    val category: ArrayList<String>? = null
)