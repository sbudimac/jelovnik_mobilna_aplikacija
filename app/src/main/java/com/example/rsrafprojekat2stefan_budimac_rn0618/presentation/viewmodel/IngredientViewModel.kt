package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Ingredient
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Resource
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository.IngredientRepository
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract.IngredientContract
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state.IngredientsState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class IngredientViewModel(
    private val ingredientRepository: IngredientRepository
) : ViewModel(), IngredientContract.ViewModel {
    private val subscriptions = CompositeDisposable()
    override val ingredientState: MutableLiveData<IngredientsState> = MutableLiveData()
    override var ingredients: MutableLiveData<List<Ingredient>> = MutableLiveData(ArrayList())
    override fun fetchAllRecipeIngredients(rId: String) {
        val subscription = ingredientRepository
            .fetchAllRecipeIngredients(rId)
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> ingredientState.value = IngredientsState.Loading
                        is Resource.Success -> ingredientState.value = IngredientsState.DataFetched
                        is Resource.Error -> ingredientState.value = IngredientsState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    ingredientState.value = IngredientsState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllRecipeIngredients(rId: String) {
        val subscription = ingredientRepository
            .getAllRecipeIngredients(rId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    ingredientState.value = IngredientsState.Success(it)
                    ingredients.value = it
                },
                {
                    ingredientState.value = IngredientsState.Error("Error happened while fetching data from database")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
}