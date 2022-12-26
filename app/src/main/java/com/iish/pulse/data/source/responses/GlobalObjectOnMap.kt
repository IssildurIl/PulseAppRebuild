package com.iish.pulse.data.source.responses

import com.google.gson.annotations.SerializedName

data class GlobalObjectOnMap(
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("points")
    val pulsePoints: List<PulseGlobalPoint>
)
