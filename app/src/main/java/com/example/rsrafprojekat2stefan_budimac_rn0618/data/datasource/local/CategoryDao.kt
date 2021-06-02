package com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local

import androidx.room.*
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.CategoryEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllCategories(entities: List<CategoryEntity>): Completable

    @Query("SELECT * FROM categories")
    abstract fun getAllCategories(): Observable<List<CategoryEntity>>

    @Query("DELETE FROM categories")
    abstract fun deleteAllCategories()

    @Transaction
    open fun deleteAndInsertAllCategories(entities: List<CategoryEntity>) {
        deleteAllCategories()
        insertAllCategories(entities).blockingAwait()
    }
}