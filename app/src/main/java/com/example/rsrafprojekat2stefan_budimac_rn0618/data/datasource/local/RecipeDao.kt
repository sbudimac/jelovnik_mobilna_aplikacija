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

    @Query("SELECT * FROM recipes WHERE title LIKE '%' || :category || '%'")
    abstract fun getByCategory(category: String): Observable<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE title LIKE '%' || :meal || '%'")
    abstract fun getByMeal(meal: String): Observable<List<RecipeEntity>>
}