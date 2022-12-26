package com.iish.pulse.data.source.responses


import com.google.gson.annotations.SerializedName

data class UserSubscriptionResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("subscription")
    val subscription: Boolean
)