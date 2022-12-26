package com.iish.pulse.data.source.responses

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "PublicationType")
class PublicationTypeResult(
    @PrimaryKey
    val id: String,
    val name: String,
    val value: String
)
