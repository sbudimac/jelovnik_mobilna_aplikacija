package com.example.rsrafprojekat2stefan_budimac_rn0618.modules

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.database.CategoryDataBase
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.remote.IngredientService
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository.IngredientRepository
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository.IngredientRepositoryImpl
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel.IngredientViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ingredientModule = module {
    viewModel { IngredientViewModel(ingredientRepository = get()) }

    single<IngredientRepository> { IngredientRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<CategoryDataBase>().getIngredientDao() }

    single<IngredientService> { create(retrofit = get()) }
}