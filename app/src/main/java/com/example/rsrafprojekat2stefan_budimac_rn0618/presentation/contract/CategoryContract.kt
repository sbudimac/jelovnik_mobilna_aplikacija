package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract

import androidx.lifecycle.LiveData
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state.CategoriesState

interface CategoryContract {

    interface ViewModel {
        val categoryState: LiveData<CategoriesState>

        fun fetchAllCategories()
        fun getAllCategories()
    }
}