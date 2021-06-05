package com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local

import androidx.room.*
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.RecipeEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllRecipes(entities: List<RecipeEntity>): Completable

    @Query("SELECT * FROM recipes")
    abstract fun getAllRecipes(): Observable<List<RecipeEntity>>

    @Query("DELETE FROM recipes")
    abstract fun deleteAllRecipes()

    @Transaction
    open fun deleteAndInsertAllRecipes(entities: List<RecipeEntity>) {
        deleteAllRecipes()
        insertAllRecipes(entities).blockingAwait()
    }

    @Query("SELECT * FROM recipes WHERE recipes.recipe_id LIKE :id")
    abstract fun getById(id: String): Observable<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE title LIKE '%' || :category || '%'")
    abstract fun getByCategory(category: String): Observable<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE title LIKE '%' || :input || '%'")
    abstract fun getByMeal(input: String): Observable<List<RecipeEntity>>

    @Query("SELECT * FROM recipes JOIN ingredients ON recipes.recipe_id = ingredients.recipe_id WHERE ingredients.name LIKE '%' || :input || '%'")
    abstract fun getByIngredient(input: String): Observable<List<RecipeEntity>>
}