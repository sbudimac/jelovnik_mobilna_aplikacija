package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Resource
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository.RecipeRepository
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract.RecipeContract
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state.RecipesState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class RecipeViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel(), RecipeContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val recipeState: MutableLiveData<RecipesState> = MutableLiveData()
    override fun fetchAllRecipes(q: String) {
        val subscription = recipeRepository
            .fetchAllRecipes(q)
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> recipeState.value = RecipesState.Loading
                        is Resource.Success -> recipeState.value = RecipesState.DataFetched
                        is Resource.Error -> recipeState.value = RecipesState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    recipeState.value = RecipesState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByCategory(category: String) {
        val subscription = recipeRepository
            .getAllByCategory(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    recipeState.value = RecipesState.Success(it)
                },
                {
                    recipeState.value = RecipesState.Error("Error happened while fetching data from database")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByMeal(meal: String) {
        val subscription = recipeRepository
            .getAllByCategory(meal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    recipeState.value = RecipesState.Success(it)
                },
                {
                    recipeState.value = RecipesState.Error("Error happened while fetching data from database")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByIngredient(ingredient: String) {
        val subscription = recipeRepository
            .getAllByCategory(ingredient)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    recipeState.value = RecipesState.Success(it)
                },
                {
                    recipeState.value = RecipesState.Error("Error happened while fetching data from database")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    private val publishSubject: PublishSubject<String> = PublishSubject.create()


}