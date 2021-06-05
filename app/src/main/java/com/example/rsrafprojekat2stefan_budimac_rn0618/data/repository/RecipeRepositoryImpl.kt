package com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.RecipeDao
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.remote.RecipeService
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Ingredient
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Recipe
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.RecipeEntity
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Resource
import io.reactivex.Observable
import timber.log.Timber

class RecipeRepositoryImpl(
    private val localDataSource: RecipeDao,
    private val remoteDataSource: RecipeService
) : RecipeRepository {

    override fun fetchAllRecipes(q: String): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAllRecipes(q)
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.recipes.map {
                    RecipeEntity(
                        it.imageUrl,
                        it.socialUrl,
                        it.publisher.trim(),
                        it.id,
                        it.title.trim()
                    )
                }
                localDataSource.deleteAndInsertAllRecipes(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAllByCategory(category: String): Observable<List<Recipe>> {
        return localDataSource
            .getByCategory(category)
            .map {
                it.map {
                    Recipe(
                        it.imageUrl,
                        it.socialUrl,
                        it.publisher,
                        it.recipeId,
                        it.title,
                        ArrayList()
                    )
                }
            }
    }

    override fun getAllByMeal(meal: String): Observable<List<Recipe>> {
        return localDataSource
            .getByMeal(meal)
            .map {
                it.map {
                    Recipe(
                        it.imageUrl,
                        it.socialUrl,
                        it.publisher,
                        it.recipeId,
                        it.title,
                        ArrayList()
                    )
                }
            }
    }

    override fun getAllByIngredient(ingredient: String): Observable<List<Recipe>> {
        return localDataSource
            .getByIngredient(ingredient)
            .map {
                it.map {
                    Recipe(
                        it.imageUrl,
                        it.socialUrl,
                        it.publisher,
                        it.recipeId,
                        it.title,
                        ArrayList()
                    )
                }
            }
    }
}