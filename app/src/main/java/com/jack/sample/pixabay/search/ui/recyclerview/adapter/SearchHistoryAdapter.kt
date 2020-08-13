package com.jack.sample.pixabay.search.ui.recyclerview.adapter

import android.view.ViewGroup
import com.jack.baselibrary.recyclerview.adapter.BaseViewAdapter
import com.jack.baselibrary.recyclerview.viewholder.BaseViewHolder
import com.jack.sample.pixabay.search.ui.recyclerview.viewholder.SearchHistoryViewHolder

class SearchHistoryAdapter(private val onClicked: ((item: String) -> Unit)?) :
    BaseViewAdapter<String>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<String> {
        return SearchHistoryViewHolder(
            parent,
            onClicked
        )
    }

    override fun onBind(viewHolder: BaseViewHolder<String>, position: Int) {
        getItem(position)?.let {
            viewHolder.bind(it)
        }
    }
}