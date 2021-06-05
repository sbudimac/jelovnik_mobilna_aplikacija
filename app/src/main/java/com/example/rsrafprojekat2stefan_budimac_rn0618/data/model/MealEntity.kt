package com.example.rsrafprojekat2stefan_budimac_rn0618.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "meals")
data class MealEntity(
    @PrimaryKey
    val title: String,
    val image: String,
    @ColumnInfo(name = "recipe_id")
    val recipeId: String,
    val date: Date = Date(),
    val category: String
)