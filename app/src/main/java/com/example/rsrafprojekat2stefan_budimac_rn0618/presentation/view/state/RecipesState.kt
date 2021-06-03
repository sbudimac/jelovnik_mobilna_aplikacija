package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Recipe

sealed class RecipesState {

    object Loading: RecipesState()
    object DataFetched: RecipesState()
    data class Success(val recipes: List<Recipe>): RecipesState()
    data class Error(val message: String): RecipesState()
}