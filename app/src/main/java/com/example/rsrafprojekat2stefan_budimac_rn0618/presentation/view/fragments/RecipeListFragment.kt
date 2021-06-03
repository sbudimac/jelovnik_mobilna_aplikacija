package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.rsrafprojekat2stefan_budimac_rn0618.R
import com.example.rsrafprojekat2stefan_budimac_rn0618.databinding.FragmentRecipeListBinding
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract.RecipeContract
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.adapter.RecipeAdapter
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state.RecipesState
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel.RecipeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {

    private val recipeViewModel: RecipeContract.ViewModel by sharedViewModel<RecipeViewModel>()
    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RecipeAdapter

    private var q:String = ""

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
            q = it
        }
        init()
    }

    private fun init() {
        initUid()
        initObservers()
    }

    private fun initUid() {
        initRecycler()
    }

    private fun initRecycler() {
        binding.recipesRv.layoutManager = LinearLayoutManager(context)
        adapter = RecipeAdapter(Glide.with(this)) {
            val args = Bundle()
        }
        binding.recipesRv.adapter = adapter
    }

    private fun initObservers() {
        recipeViewModel.recipeState.observe(viewLifecycleOwner, {
            Timber.e(it.toString())
            renderState(it)
        })
        Timber.e("WOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO%s", q)
        recipeViewModel.getAllByCategory(q)
        recipeViewModel.fetchAllRecipes(q)
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
        binding.recipeSearchView.isVisible = !loading
        binding.recipesRv.isVisible = !loading
        binding.loadingPbRecipes.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}