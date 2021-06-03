package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.rsrafprojekat2stefan_budimac_rn0618.R
import com.example.rsrafprojekat2stefan_budimac_rn0618.databinding.ActivityCategoryBinding
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.fragments.CategoryListFragment

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.categories_fcv, CategoryListFragment())
        transaction.commit()
    }
}