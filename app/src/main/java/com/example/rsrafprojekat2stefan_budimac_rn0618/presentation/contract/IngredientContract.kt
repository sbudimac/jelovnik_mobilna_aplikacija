package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract

import androidx.lifecycle.LiveData
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Ingredient
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state.IngredientsState

interface IngredientContract {

    interface ViewModel {
        val ingredientState: LiveData<IngredientsState>
        val ingredients: LiveData<List<Ingredient>>

        fun fetchAllRecipeIngredients(rId: String)
        fun getAllRecipeIngredients(rId: String)
    }
}