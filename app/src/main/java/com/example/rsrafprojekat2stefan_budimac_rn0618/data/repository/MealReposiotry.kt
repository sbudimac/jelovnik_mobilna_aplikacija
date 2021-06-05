package com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Meal
import io.reactivex.Observable

interface MealReposiotry {

    fun getAllMeals(): Observable<List<Meal>>
    fun insertMeal(meal: Meal)
}