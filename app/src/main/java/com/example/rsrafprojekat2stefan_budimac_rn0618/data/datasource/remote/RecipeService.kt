package com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.remote

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("v2/recipes/")
    fun getAllRecipes(@Query("q") q: String, @Query("page") page: Int = 1): Observable<SearchResponse>
}