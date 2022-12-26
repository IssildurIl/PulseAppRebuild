package com.iish.pulse.data.source.responses


import com.google.gson.annotations.SerializedName

data class PagePreview(
    @SerializedName("address")
    val address: String,
    @SerializedName("countComments")
    val countComments: Int,
    @SerializedName("countLikes")
    val countLikes: Int,
    @SerializedName("countPublications")
    val countPublications: Int,
    @SerializedName("countSubscriptions")
    val countSubscriptions: Int,
    @SerializedName("data")
    val data: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("geoposition")
    val geoposition: List<Double>,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("publicName")
    val publicName: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("countPulse")
    val countPulse: Int,
    @SerializedName("publicationCategories")
    val publicationCategories: List<String>,
    val begin: String?="",
    val end: String?="",
    val webSite: String?=""
)