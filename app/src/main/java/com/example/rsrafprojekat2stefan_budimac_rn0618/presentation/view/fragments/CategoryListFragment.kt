package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.rsrafprojekat2stefan_budimac_rn0618.R
import com.example.rsrafprojekat2stefan_budimac_rn0618.databinding.FragmentCategoryListBinding
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract.CategoryContract
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.activities.CategoryActivity
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.adapter.CategoryAdapter
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state.CategoriesState
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel.CategoryViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class CategoryListFragment : Fragment(R.layout.fragment_category_list) {

    private val categoryViewModel: CategoryContract.ViewModel by sharedViewModel<CategoryViewModel>()
    private var _binding: FragmentCategoryListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.categoriesRv.layoutManager = LinearLayoutManager(context)
        adapter = CategoryAdapter(Glide.with(this)) {
            (activity as CategoryActivity).supportFragmentManager.commit {
                var fragment: Fragment?
                fragment = RecipeListFragment().apply {
                    arguments = Bundle().apply {
                        putString("category", it.title)
                    }
                }
                val transaction: FragmentTransaction =
                    (activity as CategoryActivity).supportFragmentManager.beginTransaction()
                transaction.replace(R.id.categories_fcv, fragment)
                transaction.commit()
            }
        }
        binding.categoriesRv.adapter = adapter
    }

    private fun initListeners() {

    }

    private fun initObservers() {
        categoryViewModel.categoryState.observe(viewLifecycleOwner, {
            Timber.e(it.toString())
            renderState(it)
        })
        categoryViewModel.getAllCategories()
        categoryViewModel.fetchAllCategories()
    }

    private fun renderState(state: CategoriesState) {
        when (state) {
            is CategoriesState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.categories)
            }
            is CategoriesState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is CategoriesState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is CategoriesState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.categorySearchView.isVisible = !loading
        binding.categoriesRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}