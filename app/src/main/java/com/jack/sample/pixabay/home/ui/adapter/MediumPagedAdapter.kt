package com.jack.sample.pixabay.home.ui.adapter

import android.view.ViewGroup
import com.jack.baselibrary.recyclerview.adapter.BasePagedAdapter
import com.jack.baselibrary.recyclerview.viewholder.BaseViewHolder
import com.jack.sample.pixabay.base.recyclerview.viewerholder.MediumViewHolder
import com.jack.sample.pixabay.base.recyclerview.item.MediumCardItem

class MediumPagedAdapter(
) : BasePagedAdapter<MediumCardItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MediumCardItem> {
        return MediumViewHolder(parent)
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder<MediumCardItem>, position: Int) {
        getItem(position)?.let {
            viewHolder.bind(it)
        }
    }

}