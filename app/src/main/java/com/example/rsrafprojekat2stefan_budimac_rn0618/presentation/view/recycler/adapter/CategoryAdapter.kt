package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Category
import com.example.rsrafprojekat2stefan_budimac_rn0618.databinding.LayoutCategoryItemBinding
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.diff.CategoryDiffCallback
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.viewholder.CategoryViewHolder

class CategoryAdapter: ListAdapter<Category, CategoryViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemBinding = LayoutCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}