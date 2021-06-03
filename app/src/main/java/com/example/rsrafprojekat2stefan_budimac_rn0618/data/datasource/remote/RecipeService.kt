package com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.remote

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.RecipesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("recipes/?q={q}&page={page}")
    fun getAllRecipes(@Query("q") q: String, @Query("page") page: Int = 1): Observable<RecipesResponse>
}