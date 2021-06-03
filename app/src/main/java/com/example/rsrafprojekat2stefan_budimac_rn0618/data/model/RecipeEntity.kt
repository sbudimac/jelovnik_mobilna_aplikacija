package com.example.rsrafprojekat2stefan_budimac_rn0618.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "social_url")
    val socialUrl: Double,
    val publisher: String,
    @PrimaryKey
    @ColumnInfo(name = "recipe_id")
    val recipeId: String,
    val title: String
)