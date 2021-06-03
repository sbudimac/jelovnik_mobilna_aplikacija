package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Recipe

class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }
}