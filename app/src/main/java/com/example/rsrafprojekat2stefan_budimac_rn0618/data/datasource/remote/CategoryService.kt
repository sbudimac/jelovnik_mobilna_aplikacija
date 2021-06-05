package com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.remote

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.CategoryResponse
import retrofit2.http.GET
import io.reactivex.Observable

interface CategoryService {

    @GET("v2/categories")
    fun getAllCategories(): Observable<CategoryResponse>
}