package com.iish.pulse.data.source.responses


import com.google.gson.annotations.SerializedName

data class OrganizationSettings(
    @SerializedName("adress")
    val adress: String,
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("description")
    val description: String,
    @SerializedName("geoposition")
    val geoposition: List<Double>,
    @SerializedName("mail")
    val mail: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("publicName")
    val publicName: String,
    @SerializedName("regionCode")
    val regionCode: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("website")
    val website: Any?
)