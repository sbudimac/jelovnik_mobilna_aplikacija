package com.example.rsrafprojekat2stefan_budimac_rn0618.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class IngredientEntity(
    @PrimaryKey
    val name: String,
    @ColumnInfo(name = "recipe_id")
    val recipeId: String
)
