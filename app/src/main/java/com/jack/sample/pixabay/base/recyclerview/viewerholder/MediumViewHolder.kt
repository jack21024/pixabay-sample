package com.jack.sample.pixabay.base.recyclerview.viewerholder

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jack.baselibrary.recyclerview.viewholder.BaseViewHolder
import com.jack.sample.pixabay.R
import com.jack.sample.pixabay.base.recyclerview.item.MediumCardItem
import kotlinx.android.synthetic.main.item_medium_large.view.*

class MediumViewHolder(parent: ViewGroup ) : BaseViewHolder<MediumCardItem>(R.layout.item_medium_large, parent) {

    override fun bind(data: MediumCardItem) {
        itemView.apply {
            Glide.with(context)
                .load(data.imageUrl)
                .into(this.image)
        }
    }

    override fun onItemClicked(view: View) {
    }
}