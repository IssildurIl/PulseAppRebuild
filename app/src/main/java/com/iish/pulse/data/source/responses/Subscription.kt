package com.iish.pulse.data.source.responses


import com.google.gson.annotations.SerializedName

data class Subscription(
    @SerializedName("end")
    val end: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rules")
    val rules: List<Rule>,
    @SerializedName("type")
    val type: String,
    @SerializedName("typeId")
    val typeId: String
)