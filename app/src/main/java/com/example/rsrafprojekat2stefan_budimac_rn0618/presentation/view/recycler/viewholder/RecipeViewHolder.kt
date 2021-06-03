package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Recipe
import com.example.rsrafprojekat2stefan_budimac_rn0618.databinding.LayoutRecipeItemBinding

class RecipeViewHolder(private val itemBinding: LayoutRecipeItemBinding, private val glide: RequestManager, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.root.setOnClickListener {
            onItemClicked.invoke(absoluteAdapterPosition)
        }
    }

    fun bind(recipe: Recipe) {
        glide.load(recipe.imageUrl).dontAnimate().into(itemBinding.recipeImg)
        itemBinding.recipeTitle.text = recipe.title
        itemBinding.recipeAuthor.text = recipe.publisher
    }
}