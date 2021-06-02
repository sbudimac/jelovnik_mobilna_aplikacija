package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rsrafprojekat2stefan_budimac_rn0618.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {

    }
}