package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Recipe
import com.example.rsrafprojekat2stefan_budimac_rn0618.databinding.LayoutRecipeItemBinding
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.diff.CategoryDiffCallback
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.diff.RecipeDiffCallback
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.viewholder.RecipeViewHolder

class RecipeAdapter(private val glide: RequestManager, private val onCategoryClicked: (Recipe) -> Unit): ListAdapter<Recipe, RecipeViewHolder>(
    RecipeDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding = LayoutRecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(itemBinding, glide) {
            val recipe = getItem(it)
            onCategoryClicked.invoke(recipe)
        }
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}