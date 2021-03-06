package com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Recipe
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Resource
import io.reactivex.Observable

interface RecipeRepository {

    fun fetchAllRecipes(q: String): Observable<Resource<Unit>>
    fun getAllById(id: String): Observable<List<Recipe>>
    fun getAllByCategory(category: String): Observable<List<Recipe>>
    fun getAllByMeal(meal: String): Observable<List<Recipe>>
    fun getAllByIngredient(ingredient: String): Observable<List<Recipe>>
}