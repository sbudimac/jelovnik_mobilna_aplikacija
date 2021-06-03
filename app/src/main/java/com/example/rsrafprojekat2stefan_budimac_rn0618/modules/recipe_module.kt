package com.example.rsrafprojekat2stefan_budimac_rn0618.modules

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.database.CategoryDataBase
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.remote.RecipeService
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository.RecipeRepository
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository.RecipeRepositoryImpl
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel.RecipeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val recipeModule = module {
    viewModel { RecipeViewModel(recipeRepository = get()) }

    single<RecipeRepository> { RecipeRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<CategoryDataBase>().getRecipeDao() }

    single<RecipeService> { create(retrofit = get()) }
}