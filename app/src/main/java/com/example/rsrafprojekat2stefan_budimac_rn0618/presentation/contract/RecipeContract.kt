package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract

import androidx.lifecycle.LiveData
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Recipe
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state.RecipesState
import io.reactivex.Observable

interface RecipeContract {

    interface ViewModel {
        val recipeState: LiveData<RecipesState>

        fun fetchAllRecipes(q: String)
        fun getAllByCategory(category: String)
        fun getAllByMeal(meal: String)
        fun getAllByIngedient(ingredient: String)
    }
}