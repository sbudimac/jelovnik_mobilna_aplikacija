package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract

import androidx.lifecycle.LiveData
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Meal
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Recipe
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state.MealsState

interface MealContract {

    interface ViewModel {
        val mealState: LiveData<MealsState>

        fun getAllMeals()
        fun insertMeal(meal: Meal)
    }
}