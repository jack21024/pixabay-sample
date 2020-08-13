package com.jack.sample.pixabay.home.ui.recyclerview.viewerholder

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jack.baselibrary.recyclerview.viewholder.BaseViewHolder
import com.jack.sample.pixabay.R
import com.jack.sample.pixabay.home.ui.recyclerview.item.MediumCardItem
import kotlinx.android.synthetic.main.item_medium.view.*

class MediumViewHolder(parent: ViewGroup ) : BaseViewHolder<MediumCardItem>(R.layout.item_medium, parent) {

    override fun bind(data: MediumCardItem) {
        itemView.apply {
            text_user_name.text = data.userName
            text_likes.text = data.likes.toString()
            text_stars.text = data.stars.toString()
            text_comments.text = data.stars.toString()
            Glide.with(context)
                .load(data.imageUrl)
                .placeholder(R.drawable.draw_medium_placholder)
                .into(this.image_medium)
        }
    }

    override fun onItemClicked(view: View) = Unit
}