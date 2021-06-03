package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Category
import com.example.rsrafprojekat2stefan_budimac_rn0618.databinding.LayoutCategoryItemBinding

class CategoryViewHolder(private val itemBinding: LayoutCategoryItemBinding, private val glide: RequestManager, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.root.setOnClickListener {
            onItemClicked.invoke(absoluteAdapterPosition)
        }
    }

    fun bind(category: Category) {
        itemBinding.categoryItem.text = category.title
        glide.load(category.imageUrl).dontAnimate().into(itemBinding.categoryImg)
    }
}