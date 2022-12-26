package com.iish.pulse.data.source.responses


import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.gson.annotations.SerializedName
import com.google.maps.android.clustering.ClusterItem

data class PulsePoint(
    @SerializedName("adress")
    val adress: String?,
    @SerializedName("geoposition")
    val geoposition: List<Double>?,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("publicationCategories")
    val publicationCategories: List<PublicationCategory>?,
    @SerializedName("publicationType")
    var publicationType: PublicationType?,
    @SerializedName("pulse")
    val pulse: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("description")
    val description: String?


) : ClusterItem {

    constructor(place: AutocompletePrediction) : this(
        place.getSecondaryText(null).toString(), null,
        place.placeId, place.getPrimaryText(null).toString(), null, null, null, null, null
    ) {
        val type = place.placeTypes.find {
            it == Place.Type.STREET_ADDRESS
        }
        this.publicationType = if (type == null) null
        else PublicationType("", type.toString())

    }

    override fun toString(): String {
        return name ?: "Publication"
    }

    override fun getPosition(): LatLng {
        return LatLng(geoposition?.get(0) ?: 0.0, geoposition?.get(1) ?: 0.0)
    }

    override fun getTitle(): String? {
        return name
    }

    override fun getSnippet(): String? {
        return publicationType?.name
    }


}