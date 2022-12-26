package com.iish.pulse.data.source.responses


import com.google.gson.annotations.SerializedName

data class RulesResponse(
    @SerializedName("basicRules")
    val basicRules: List<String>,
    @SerializedName("subscription")
    val subscription: Subscription
)