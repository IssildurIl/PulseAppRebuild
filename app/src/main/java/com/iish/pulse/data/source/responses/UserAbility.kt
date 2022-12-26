package com.iish.pulse.data.source.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


/*
    0 - не можешь ничего, 1 - может редактировать и удалять
*/

@Parcelize
data class UserAbility(
    var userLike: Int?,
    val subscribed: Int?,
    val setSettings: Int?,
    val canComment: Int?,
) : Serializable, Parcelable