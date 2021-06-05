package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Meal
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository.MealReposiotry
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract.MealContract
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state.MealsState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MealViewModel(
    private val mealReposiotry: MealReposiotry
) : ViewModel(), MealContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val mealState: MutableLiveData<MealsState> = MutableLiveData()
    override fun getAllMeals() {
        val subscription = mealReposiotry
            .getAllMeals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mealState.value = MealsState.Success(it)
                },
                {
                    mealState.value = MealsState.Error("Error happened while fetching data from database")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun insertMeal(meal: Meal) {
        mealReposiotry.insertMeal(meal)
    }
}