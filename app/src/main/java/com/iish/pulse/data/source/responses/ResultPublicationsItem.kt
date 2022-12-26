package com.iish.pulse.data.source.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class ResultPublicationsItem(
    val files: List<ImageSettings>,
    var publication: Publication,
    val user: UserEntityProfile?,
    var userAbility: UserAbility
) : Serializable, Parcelable
