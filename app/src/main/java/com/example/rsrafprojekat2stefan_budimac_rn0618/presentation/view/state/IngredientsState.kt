package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Ingredient


sealed class IngredientsState {

    object Loading: IngredientsState()
    object DataFetched: IngredientsState()
    data class Success(val ingredients: List<Ingredient>): IngredientsState()
    data class Error(val message: String): IngredientsState()
}