package com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.CategoryDao
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.IngredientDao
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.MealDao
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.RecipeDao
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.converters.DateConverter
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.converters.StringListConverter
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.CategoryEntity
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.IngredientEntity
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.MealEntity
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.RecipeEntity

@Database(
    entities = [CategoryEntity::class, RecipeEntity::class, IngredientEntity::class, MealEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class, DateConverter::class)
abstract class CategoryDataBase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao
    abstract fun getRecipeDao(): RecipeDao
    abstract fun getIngredientDao(): IngredientDao
    abstract fun getMealDao(): MealDao
}