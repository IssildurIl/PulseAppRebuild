package com.iish.pulse.data.source.request

import com.google.gson.annotations.SerializedName

data class CreateUser(
    @SerializedName("mail") val mail: String,
    @SerializedName("pass") val pass: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("publicName") val publicName: String,
    @SerializedName("image") val image: String
)