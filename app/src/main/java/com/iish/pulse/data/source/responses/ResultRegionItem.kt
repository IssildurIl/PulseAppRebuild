package com.iish.pulse.data.source.responses


import com.google.gson.annotations.SerializedName

data class ResultRegionItem(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("isChecked")
    var isChecked: Boolean = true
)