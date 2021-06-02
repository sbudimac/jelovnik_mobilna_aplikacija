package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Resource
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.repository.CategoryRepository
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract.CategoryContract
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state.CategoriesState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class CategoryViewModel(
    private val categoryRepository: CategoryRepository
) : ViewModel(), CategoryContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val categoryState: MutableLiveData<CategoriesState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                categoryRepository
                    .getAllCategories()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    categoryState.value = CategoriesState.Success(it)
                },
                {
                    categoryState.value = CategoriesState.Error("Error happened while fetching data from database")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAllCategories() {
        val subscription = categoryRepository
            .fetchAllCategories()
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> categoryState.value = CategoriesState.Loading
                        is Resource.Success -> categoryState.value = CategoriesState.DataFetched
                        is Resource.Error -> categoryState.value = CategoriesState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    categoryState.value = CategoriesState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllCategories() {
        val subscription = categoryRepository
            .getAllCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    categoryState.value = CategoriesState.Success(it)
                },
                {
                    categoryState.value = CategoriesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
}