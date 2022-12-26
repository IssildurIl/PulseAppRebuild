package com.iish.pulse.data.source.responses


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "Rules")
data class Rule(
    @PrimaryKey
    @SerializedName("rule")
    val rule: String,
    @SerializedName("count")
    val count: Int
)