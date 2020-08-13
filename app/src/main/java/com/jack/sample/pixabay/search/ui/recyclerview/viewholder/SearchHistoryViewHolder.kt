package com.jack.sample.pixabay.search.ui.recyclerview.viewholder

import android.view.View
import android.view.ViewGroup
import com.jack.baselibrary.recyclerview.viewholder.BaseViewHolder
import com.jack.sample.pixabay.R
import kotlinx.android.synthetic.main.item_search_history.view.*

class SearchHistoryViewHolder(
    parent: ViewGroup,
    private val onClicked: ((item: String) -> Unit)? = null
) :
    BaseViewHolder<String>(R.layout.item_search_history, parent) {

    private var data: String? = null

    override fun bind(data: String) {
        this.data = data
        itemView.text_keyword.text = data
    }

    override fun onItemClicked(view: View) {
        data?.let {
            onClicked?.invoke(it)
        }
    }
}