package com.iish.pulse.data.source.responses


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("data")
    val imageData: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("mail")
    val mail: String,
    @SerializedName("middlename")
    val middlename: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("publicName")
    val publicName: String,
    @SerializedName("surname")
    val surname: String?
)