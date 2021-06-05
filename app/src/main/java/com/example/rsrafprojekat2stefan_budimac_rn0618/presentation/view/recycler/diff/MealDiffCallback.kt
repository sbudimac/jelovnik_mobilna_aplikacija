package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Meal

class MealDiffCallback : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.title == newItem.title
    }
}