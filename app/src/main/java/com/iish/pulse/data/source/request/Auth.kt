package com.iish.pulse.data.source.request

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "User_table")
data class Auth(
    @ColumnInfo(name = "login")
    @SerializedName("login")
    val login: String? = null,
    @ColumnInfo(name = "password")
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "accessToken")
    val accessToken: String? = null,
    @ColumnInfo(name = "countryCode")
    val countryCode: String? = null,
)

class AuthUser(
    val login: String?,
    val password: String?,
)


