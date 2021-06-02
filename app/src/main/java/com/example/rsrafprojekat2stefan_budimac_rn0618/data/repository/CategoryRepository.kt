package com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Category
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Resource
import io.reactivex.Observable

interface CategoryRepository {

    fun fetchAllCategories(): Observable<Resource<Unit>>
    fun getAllCategories(): Observable<List<Category>>
}