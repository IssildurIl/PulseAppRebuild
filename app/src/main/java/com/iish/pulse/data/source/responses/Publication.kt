package com.iish.pulse.data.source.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Publication(
    val caption: String?,
    var countComments: Int?,
    var countLikes: Int?,
    var countPublications: Int?,
    var countPulse: Int?,
    var countViews: Int?,
    val datePublication: String?,
    val description: String,
    val end: String?,
    val begin: String?,
    val lat: Double?,
    val long:Double?,
    val id: String,
    val moderation: String,
    val publicationCategories: List<String>,
    val userId: String?,
    val address: String?,
    val webSite: String?,
    val phone: String?,
    val publicationTypeId: String,
    val publicationTypeName: String
):Serializable, Parcelable