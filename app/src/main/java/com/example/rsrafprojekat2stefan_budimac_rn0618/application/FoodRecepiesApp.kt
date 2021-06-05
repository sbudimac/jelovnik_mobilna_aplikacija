package com.example.rsrafprojekat2stefan_budimac_rn0618.application

import android.app.Application
import com.example.rsrafprojekat2stefan_budimac_rn0618.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class FoodRecepiesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            categoryModule,
            recipeModule,
            ingredientModule,
            mealModule
        )
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@FoodRecepiesApp)
            androidFileProperties()
            fragmentFactory()
            modules(modules)
        }
    }
}