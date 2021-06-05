package com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local

import androidx.room.*
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.IngredientEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class IngredientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllRecipeIngredients(entities: List<IngredientEntity>): Completable

    @Query("SELECT * FROM ingredients WHERE recipe_id = :rId")
    abstract fun getAllRecipeIngredients(rId: String): Observable<List<IngredientEntity>>

    @Query("DELETE FROM ingredients")
    abstract fun deleteAllRecipeIngredients(): Completable

    @Transaction
    open fun deleteAndInsertAllRecipeIngredients(entities: List<IngredientEntity>) {
        deleteAllRecipeIngredients()
        insertAllRecipeIngredients(entities).blockingAwait()
    }
}