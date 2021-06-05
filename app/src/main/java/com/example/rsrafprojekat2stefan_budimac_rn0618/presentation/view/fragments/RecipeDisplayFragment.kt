package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.rsrafprojekat2stefan_budimac_rn0618.R
import com.example.rsrafprojekat2stefan_budimac_rn0618.databinding.FragmentRecipeDisplayBinding
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract.IngredientContract
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.activities.CategoryActivity
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.activities.SaveRecipeActivity
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state.IngredientsState
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel.IngredientViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class RecipeDisplayFragment : Fragment(R.layout.fragment_recipe_display) {

    private val ingredientViewModel: IngredientContract.ViewModel by sharedViewModel<IngredientViewModel>()
    private var _binding: FragmentRecipeDisplayBinding? = null
    private val binding get() = _binding!!

    private var imageUrl: String = ""
    private var title: String = ""
    private var id: String = ""
    private var publisher: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeDisplayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("imageUrl")?.let {
            imageUrl = it
        }
        arguments?.getString("title")?.let {
            title = it
        }
        arguments?.getString("id")?.let {
            id = it
        }
        arguments?.getString("publisher")?.let {
            publisher = it
        }
        init()
    }

    private fun init() {
        initUi()
        initListeners()
        initObservers()
    }

    private fun initUi() {
        Glide.with(this).load(imageUrl).dontAnimate().into(binding.mealImg)
        binding.mealTitle.text = title
        binding.ingredientsList.setMovementMethod(ScrollingMovementMethod())
        ingredientViewModel.ingredients.observe(viewLifecycleOwner, Observer {
            var ingredientsTxt = ""
            for (ingredient in it) {
                ingredientsTxt += ingredient.name
                ingredientsTxt += System.lineSeparator()
            }
            binding.ingredientsList.text = ingredientsTxt
        })
    }

    private fun initListeners() {
        binding.mealBack.setOnClickListener {
            (activity as CategoryActivity).supportFragmentManager.commit {
                val transaction: FragmentTransaction =
                    (activity as CategoryActivity).supportFragmentManager.beginTransaction()
                transaction.replace(R.id.categories_fcv, CategoryListFragment())
                transaction.commit()
            }
        }

        binding.saveRecipe.setOnClickListener {
            activity?.let{
                val intent = Intent (it, SaveRecipeActivity::class.java)
                intent.putExtra("title", title)
                intent.putExtra("imageUrl", imageUrl)
                intent.putExtra("id", id)
                it.startActivity(intent)
            }
        }
    }

    private fun initObservers() {
        ingredientViewModel.ingredientState.observe(viewLifecycleOwner, {
            Timber.e(it.toString())
            renderState(it)
        })
        ingredientViewModel.getAllRecipeIngredients(id)
        ingredientViewModel.fetchAllRecipeIngredients(id)
    }

    private fun renderState(state: IngredientsState) {
        when(state) {
            is IngredientsState.Success -> {
                showLoadingState(false)
            }
            is IngredientsState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is IngredientsState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_SHORT).show()
            }
            is IngredientsState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.ingredientsList.isVisible = !loading
        binding.ingredientsTv.isVisible = !loading
        binding.meal100.isVisible = !loading
        binding.mealImg.isVisible = !loading
        binding.mealTitle.isVisible = !loading
        binding.saveRecipe.isVisible = !loading
        binding.loadingPbMeal.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}