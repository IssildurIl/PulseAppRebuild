package com.iish.pulse.data.source.responses


import com.google.gson.annotations.SerializedName

data class UserEditModel(
    @SerializedName("radiusPush")
    val radiusPush: Int,
    @SerializedName("user")
    val user: User
)