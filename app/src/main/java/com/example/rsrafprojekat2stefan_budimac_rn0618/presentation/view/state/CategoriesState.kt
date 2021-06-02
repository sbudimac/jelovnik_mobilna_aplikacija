package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Category

sealed class CategoriesState {

    object Loading: CategoriesState()
    object DataFetched: CategoriesState()
    data class Success(val categories: List<Category>): CategoriesState()
    data class Error(val message: String): CategoriesState()
}