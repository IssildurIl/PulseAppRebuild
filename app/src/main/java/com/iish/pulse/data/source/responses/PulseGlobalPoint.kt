package com.iish.pulse.data.source.responses

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import com.google.maps.android.clustering.ClusterItem

data class PulseGlobalPoint(
    @SerializedName("id")
    val id: String?,
    @SerializedName("pulse")
    val pulse: Int?,
    @SerializedName("typeName")
    var typeName: String?,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("region")
    val region: String?
    ) : ClusterItem {
    override fun getPosition(): LatLng {
        return LatLng(lat, lon)
    }

    override fun getTitle(): String? {
        return null
    }

    override fun getSnippet(): String? {
        return null
    }
}
