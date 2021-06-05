package com.example.rsrafprojekat2stefan_budimac_rn0618.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeWithIngredients(
    @Embedded val recipeEntity: RecipeEntity,
    @Relation(
        parentColumn = "recipe_id",
        entityColumn = "recipe_id"
    )
    val ingredients: List<IngredientEntity>?
)
