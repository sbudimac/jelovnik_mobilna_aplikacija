package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Category

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.title == newItem.title
    }
}