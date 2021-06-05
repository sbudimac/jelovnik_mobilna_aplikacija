package com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.remote

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.RecipeResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IngredientService {

    @GET("get")
    fun getAllRecipeIngredients(@Query("rId") rId: String): Observable<RecipeResponse>
}