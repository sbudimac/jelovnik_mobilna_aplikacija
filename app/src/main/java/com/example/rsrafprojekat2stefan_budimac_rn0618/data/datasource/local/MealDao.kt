package com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.MealEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class MealDao {

    @Query("SELECT * FROM meals")
    abstract fun getAllMeals(): Observable<List<MealEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMeal(entity: MealEntity): Completable
}