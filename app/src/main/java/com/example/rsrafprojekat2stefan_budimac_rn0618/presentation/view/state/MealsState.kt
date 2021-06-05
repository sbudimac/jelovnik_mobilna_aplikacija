package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Meal


sealed class MealsState {
    object Loading: MealsState()
    object DataFetched: MealsState()
    data class Success(val meals: List<Meal>): MealsState()
    data class Error(val message: String): MealsState()
}