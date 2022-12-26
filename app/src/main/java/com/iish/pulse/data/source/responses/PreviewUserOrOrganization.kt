package com.iish.pulse.data.source.responses


import com.google.gson.annotations.SerializedName

data class PreviewUserOrOrganization(
    @SerializedName("pagePreview")
    val pagePreview: PagePreview,
    @SerializedName("userAbility")
    val userAbility: UserAbility,
)