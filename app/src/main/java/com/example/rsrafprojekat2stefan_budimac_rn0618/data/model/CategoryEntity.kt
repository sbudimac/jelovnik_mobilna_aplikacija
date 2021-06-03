package com.example.rsrafprojekat2stefan_budimac_rn0618.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "image_url")
    val imageURL: String,
    val title: String
)