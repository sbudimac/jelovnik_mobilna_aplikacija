package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.rsrafprojekat2stefan_budimac_rn0618.R
import com.example.rsrafprojekat2stefan_budimac_rn0618.databinding.FragmentRecipeListBinding
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract.IngredientContract
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract.RecipeContract
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.activities.CategoryActivity
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.adapter.RecipeAdapter
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state.RecipesState
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel.IngredientViewModel
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel.RecipeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {

    private val recipeViewModel: RecipeContract.ViewModel by sharedViewModel<RecipeViewModel>()
    private val ingredientViewModel: IngredientContract.ViewModel by sharedViewModel<IngredientViewModel>()
    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RecipeAdapter

    private var category: String = ""
    private var recipe: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("category")?.let {
            category = it
        }
        arguments?.getString("recipe")?.let {
            recipe = it
        }
        init()
    }

    private fun init() {
        initUid()
        initObservers()
    }

    private fun initUid() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.recipesRv.layoutManager = LinearLayoutManager(context)
        adapter = RecipeAdapter(Glide.with(this)) {
            (activity as CategoryActivity).supportFragmentManager.commit {
                var fragment: Fragment?
                fragment = RecipeDisplayFragment().apply {
                    arguments = Bundle().apply {
                        putString("imageUrl", it.imageUrl)
                        putString("title", it.title)
                        putString("id", it.id)
                        putString("publisher", it.publisher)
                    }
                }
                val transaction: FragmentTransaction =
                    (activity as CategoryActivity).supportFragmentManager.beginTransaction()
                transaction.replace(R.id.categories_fcv, fragment)
                transaction.commit()
            }
        }
        binding.recipesRv.adapter = adapter
    }

    private fun initListeners() {
        binding.recipeSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                (activity as CategoryActivity).supportFragmentManager.commit {
                    var fragment: Fragment?
                    fragment = RecipeListFragment().apply {
                        arguments = Bundle().apply {
                            putString("recipe", p0)
                        }
                    }
                    val transaction: FragmentTransaction =
                        (activity as CategoryActivity).supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.categories_fcv, fragment)
                    transaction.commit()
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        }

        )
        binding.recipeBack.setOnClickListener {
            (activity as CategoryActivity).supportFragmentManager.commit {
                val transaction: FragmentTransaction =
                    (activity as CategoryActivity).supportFragmentManager.beginTransaction()
                transaction.replace(R.id.categories_fcv, CategoryListFragment())
                transaction.commit()
            }
        }
    }

    private fun initObservers() {
        recipeViewModel.recipeState.observe(viewLifecycleOwner, {
            Timber.e(it.toString())
            renderState(it)
        })
        if (category == "") {
            //ingredientViewModel.getAllRecipeIngredients(rId = )
            //ingredientViewModel.fetchAllRecipeIngredients(id)
            recipeViewModel.getAllByMeal(recipe)
            recipeViewModel.getAllByIngredient(recipe)
            recipeViewModel.fetchAllRecipes(recipe)
        } else {
            recipeViewModel.getAllByCategory(category)
            recipeViewModel.fetchAllRecipes(category)
        }
    }

    private fun renderState(state: RecipesState) {
        when(state) {
            is RecipesState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.recipes)
            }
            is RecipesState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is RecipesState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_SHORT).show()
            }
            is RecipesState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.recipeBack.isVisible = !loading
        binding.recipeSearchView.isVisible = !loading
        binding.recipesRv.isVisible = !loading
        binding.loadingPbRecipes.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}