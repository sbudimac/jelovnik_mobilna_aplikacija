package com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.CategoryDao
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.RecipeDao
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.CategoryEntity
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.RecipeEntity

@Database(
    entities = [CategoryEntity::class, RecipeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters()
abstract class CategoryDataBase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao
    abstract fun getRecipeDao(): RecipeDao
}