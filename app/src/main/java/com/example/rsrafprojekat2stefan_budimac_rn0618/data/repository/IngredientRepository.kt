package com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Ingredient
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Resource
import io.reactivex.Observable

interface IngredientRepository {

    fun fetchAllRecipeIngredients(rId: String): Observable<Resource<Unit>>
    fun getAllRecipeIngredients(rId: String): Observable<List<Ingredient>>
}