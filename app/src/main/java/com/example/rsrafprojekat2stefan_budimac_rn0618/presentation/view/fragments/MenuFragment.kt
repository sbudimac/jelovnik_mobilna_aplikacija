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
import com.example.rsrafprojekat2stefan_budimac_rn0618.databinding.FragmentMenuBinding
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.contract.MealContract
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.activities.CategoryActivity
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.adapter.MenuAdapter
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.state.MealsState
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.viewmodel.MealViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class MenuFragment : Fragment(R.layout.fragment_menu) {
    private val mealViewModel: MealContract.ViewModel by sharedViewModel<MealViewModel>()
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObserver()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.menusRv.layoutManager = LinearLayoutManager(context)
        adapter = MenuAdapter(Glide.with(this)) {
            (activity as CategoryActivity).supportFragmentManager.commit {
                var fragment: Fragment?
            }
        }
        binding.menusRv.adapter = adapter
    }

    private fun initListeners() {
        binding.menuBack.setOnClickListener {
            (activity as CategoryActivity).supportFragmentManager.commit {
                val transaction: FragmentTransaction =
                    (activity as CategoryActivity).supportFragmentManager.beginTransaction()
                transaction.replace(R.id.categories_fcv, CategoryListFragment())
                transaction.commit()
            }
        }
    }

    private fun initObserver() {
        mealViewModel.mealState.observe(viewLifecycleOwner, {
            Timber.e(it.toString())
            renderState(it)
        })
        mealViewModel.getAllMeals()
    }

    private fun renderState(state: MealsState) {
        when (state) {
            is MealsState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.meals)
            }
            is MealsState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is MealsState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is MealsState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.menusRv.isVisible = !loading
        binding.menuBack.isVisible = !loading
        binding.loadingPbMenus.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}