package com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.CategoryDao
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.remote.CategoryService
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Category
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.CategoryEntity
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Resource
import io.reactivex.Observable

class CategoryRepositoryImpl(
    private val localDataSource: CategoryDao,
    private val remoteDataSource: CategoryService
) : CategoryRepository {

    override fun fetchAllCategories(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAllCategories()
            .doOnNext {
                val entities = it.categories.map {
                    CategoryEntity(
                        it.title,
                        it.imageUrl
                    )
                }
                localDataSource.deleteAndInsertAllCategories(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAllCategories(): Observable<List<Category>> {
        return localDataSource
            .getAllCategories()
            .map {
                it.map {
                    Category(
                        it.imageURL,
                        it.title
                    )
                }
            }
    }
}