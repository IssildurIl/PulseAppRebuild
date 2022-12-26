package com.iish.pulse.data.source.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class UserEntityProfile(
    val isUser: Boolean,
    val data: String?,
    val id: String,
    val name: String?,
    val publicName: String,
) : Serializable,Parcelable

data class UserPreview(
    val countPublications: Int,
    val countUsersSubscription: Int,
    val data: String?,
    val id: String,
    val name: String?,
    val publicName: String,
) : Serializable