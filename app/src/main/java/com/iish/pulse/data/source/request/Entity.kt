package com.iish.pulse.data.source.request

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Entity(
    @SerializedName("id")
    val id: String
)

data class PubFilter(
    @SerializedName("typeName")
    val name: String?,
    @SerializedName("category")
    var category: Collection<String>?,
    @SerializedName("lastId")
    var lastId: String?,
    @SerializedName("regionCodes")
    var regionCode: Collection<String>?
) : Serializable


data class UserPubFilter(
    @SerializedName("id")
    val id: String,
    @SerializedName("typeName")
    var typeName: String = "ALL",
    @SerializedName("lastId")
    var lastId: String?,
) : Serializable {
    constructor(id: String) : this(id, lastId = null)

    constructor(id: String, typeName: PublicationType) : this(
        id,
        typeName.toString(),
        lastId = null
    )

    constructor(id: String, lastId: String) : this(id, "All", lastId)

    constructor(id: String, typeName: PublicationType, lastId: String) : this(
        id,
        typeName.toString(),
        lastId
    )
}

data class DbPubFilter(
    @ColumnInfo(name = "PublicationType.name")
    val publicationType: List<String>
)

enum class PublicationType {
    Post,
    Event
}