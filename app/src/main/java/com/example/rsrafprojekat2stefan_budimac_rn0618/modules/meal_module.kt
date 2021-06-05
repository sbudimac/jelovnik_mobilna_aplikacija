package com.example.rsrafprojekat2stefan_budimac_rn0618.modules

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.database.CategoryDataBase
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository.MealReposiotry
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository.MealRepositoryImpl
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel.MealViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mealModule = module {
    viewModel { MealViewModel(mealReposiotry = get()) }

    single<MealReposiotry> { MealRepositoryImpl(localDataSource = get()) }

    single { get<CategoryDataBase>().getMealDao() }
}