package com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.IngredientDao
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.remote.IngredientService
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Ingredient
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.IngredientEntity
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Resource
import io.reactivex.Observable
import timber.log.Timber

class IngredientRepositoryImpl(
    private val localDataSource: IngredientDao,
    private val remoteDataSource: IngredientService
) : IngredientRepository {
    override fun fetchAllRecipeIngredients(rId: String): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAllRecipeIngredients(rId)
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.recipe.ingredients.map {
                    IngredientEntity(
                        it,
                        rId
                    )
                }
                localDataSource.deleteAndInsertAllRecipeIngredients(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAllRecipeIngredients(rId: String): Observable<List<Ingredient>> {
        return localDataSource
            .getAllRecipeIngredients(rId)
            .map {
                it.map {
                    Ingredient(
                        it.name
                    )
                }
            }
    }

}