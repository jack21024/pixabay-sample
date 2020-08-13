package com.jack.sample.pixabay.home.ui.recyclerview.adapter

import android.view.ViewGroup
import com.jack.baselibrary.recyclerview.adapter.BasePagedAdapter
import com.jack.baselibrary.recyclerview.viewholder.BaseViewHolder
import com.jack.sample.pixabay.home.ui.recyclerview.viewerholder.MediumViewHolder
import com.jack.sample.pixabay.home.ui.recyclerview.item.MediumCardItem
import com.jack.sample.pixabay.home.ui.recyclerview.viewerholder.GridMediumViewHolder
import com.jack.sample.pixabay.home.enums.MediumLayoutStyle

class MediumPagedAdapter(
    val style: MediumLayoutStyle = MediumLayoutStyle.LIST
) : BasePagedAdapter<MediumCardItem>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MediumCardItem> {
        return when (style) {
            MediumLayoutStyle.GRID -> GridMediumViewHolder(parent)
            else -> MediumViewHolder(parent)
        }
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder<MediumCardItem>, position: Int) {
        getItem(position)?.let {
            viewHolder.bind(it)
        }
    }

}