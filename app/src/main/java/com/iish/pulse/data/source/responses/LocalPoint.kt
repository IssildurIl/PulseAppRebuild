package com.iish.pulse.data.source.responses

import android.location.Address
import com.google.android.libraries.places.api.model.Place

class LocalPoint {
    val name: String
    val description: String
    val lat: Double
    val lon: Double
    val postalCode: String

    constructor(address: Address?) {
        name = getName(address)
        description = getDescription(address)
        lat = address?.latitude ?: 1.0
        lon = address?.longitude ?: 1.0
        postalCode = address?.postalCode ?: "344"
    }

    constructor() {
        name = "Empty Local Point"
        description = "No data for this position"
        lat = 1.1
        lon = 1.1
        postalCode = "0"
    }

    constructor(place: Place) {
        name = place.name!!.toString()

        description = getDescription(place)
        lat = place.latLng?.latitude ?: 1.0
        lon = place.latLng?.longitude ?: 1.0
        postalCode = place.addressComponents?.asList()?.firstOrNull {
            it.types.contains("postal_code")
        }?.name.toString()
    }

    private fun getName(address: Address?): String {
        var str = ""
        if (address?.thoroughfare != null
            && address.thoroughfare != address.featureName
        ) {
            str = address.thoroughfare + ", "
        }
        str += address?.featureName
        return str
    }

    private fun getDescription(place: Place): String {
        var str = ""

        if (place.address != null) {
            str = place.address!!.toString()
        }
        else
        {
            str += place.addressComponents?.asList()?.firstOrNull {
                it.types.contains("locality")
            }?.name.toString() + ", "
            str += place.addressComponents?.asList()?.firstOrNull {
                it.types.contains("country")
            }?.name.toString()
        }

        return str
    }

    private fun getDescription(address: Address?): String {
        var str = ""

        if (address?.locality != null) {
            str = address.locality + ", "
        }
        str += address?.countryName
        return str
    }

    override fun toString(): String {
        return name
    }

}