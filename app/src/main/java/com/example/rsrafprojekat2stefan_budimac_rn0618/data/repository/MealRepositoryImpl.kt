package com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.MealDao
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Meal
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.MealEntity
import io.reactivex.Observable

class MealRepositoryImpl(
    private val localDataSource: MealDao
) : MealReposiotry {
    override fun getAllMeals(): Observable<List<Meal>> {
        return localDataSource
            .getAllMeals()
            .map {
                it.map {
                    Meal(
                        it.title,
                        it.image,
                        it.recipeId,
                        it.date,
                        it.category
                    )
                }
            }
    }

    override fun insertMeal(meal: Meal) {
        localDataSource
            .insertMeal(
                MealEntity(
                    meal.title,
                    meal.image,
                    meal.recipeId,
                    meal.date,
                    meal.category
                )
            )
    }

}