package com.jack.sample.pixabay.home.ui.recyclerview.viewerholder

import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jack.baselibrary.recyclerview.viewholder.BaseViewHolder
import com.jack.baselibrary.utils.DisplayUtils
import com.jack.sample.pixabay.R
import com.jack.sample.pixabay.home.ui.recyclerview.item.MediumCardItem
import kotlinx.android.synthetic.main.item_medium.view.*


class MediumViewHolder(parent: ViewGroup ) : BaseViewHolder<MediumCardItem>(R.layout.item_medium, parent) {

    override fun bind(data: MediumCardItem) {
        val ratio = data.imageHeigh.toFloat() / data.imageWidth.toFloat()
        val w = DisplayUtils.screenWidth
        val h = Math.round(w.toFloat() * ratio)
        val placeholder = itemView.context.getDrawable(R.drawable.draw_placholder)?.apply {
            (this as GradientDrawable).setSize(w, h)
        }
        itemView.apply {
            image_medium.setImageResource(0)
            text_user_name.text = data.userName
            text_likes.text = data.likes.toString()
            text_stars.text = data.stars.toString()
            text_comments.text = data.stars.toString()
            Glide.with(context)
                .load(data.imageUrl)
                .placeholder(placeholder)
                .override(w, h)
                .into(this.image_medium)
        }
    }

    override fun onItemClicked(view: View) = Unit
}