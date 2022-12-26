package com.iish.pulse.data.source.request

import com.google.gson.annotations.SerializedName


data class NewPublication(
    @SerializedName("userId") val userId: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("description")  val description: String,
    @SerializedName("lat")  val lat: Double?,
    @SerializedName("lon")  val lon: Double?,
    @SerializedName("publicationCategories")  val publicationCategories: List<String>, // cant be null
    @SerializedName("publicationTypeId")  val publicationTypeId: String?, // cant be null
    @SerializedName("files")  val files: List<String>, // cant be null
    @SerializedName("begin") val begin: String?,
    @SerializedName("end") val end: String?,
    @SerializedName("CoverageRadius") val CoverageRadius: Int?,
    @SerializedName("regionCode") val regionCode: String?, // cant be null
    @SerializedName("attachTo") val attachTo: AttachTo?,
    @SerializedName("phone") val phone:String? = "",
    @SerializedName("website") val website:String? = ""
)


data class AttachTo(
    @SerializedName("id") var id: String?,
    @SerializedName("typeId") var typeId: String?
)

data class InfoById(
    @SerializedName("id") val id: String,
    @SerializedName("typeName") val typeName:String
)



