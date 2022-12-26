package com.iish.pulse.data.source.responses

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Categories")
data class CategoriesResult(
    @PrimaryKey
    val id: String,
    val name: String,
    val value: String,
    var isChecked: Boolean = true
)

@Entity(tableName = "Categories")
data class PublicationCategories(
    @PrimaryKey
    val id: String,
    val value: String
)