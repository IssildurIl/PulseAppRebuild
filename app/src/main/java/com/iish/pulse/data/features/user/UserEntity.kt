package com.iish.pulse.data.features.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserEntity.TABLE_DAILY_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = 0,
    @ColumnInfo(name = "login")
    val login: String? = "",
    @ColumnInfo(name = "password")
    val password: String? = "",
    @ColumnInfo(name = "revision")
    val revision:Int? = 0
) {
    companion object {
        const val TABLE_DAILY_NAME = "User_Entity"
    }
}
