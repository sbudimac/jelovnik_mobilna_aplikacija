package com.example.rsrafprojekat2stefan_budimac_rn0618.modules

import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.local.database.CategoryDataBase
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.datasource.remote.CategoryService
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository.CategoryRepository
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository.CategoryRepositoryImpl
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel.CategoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val categoryModule = module {
    viewModel { CategoryViewModel(categoryRepository = get()) }

    single<CategoryRepository> { CategoryRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<CategoryDataBase>().getCategoryDao() }

    single<CategoryService> { create(retrofit = get()) }
}