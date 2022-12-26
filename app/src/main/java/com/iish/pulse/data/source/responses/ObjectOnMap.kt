package com.iish.pulse.data.source.responses

import com.google.gson.annotations.SerializedName

data class ObjectOnMap(
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("points")
    val pulsePoints: List<PulsePoint>
)