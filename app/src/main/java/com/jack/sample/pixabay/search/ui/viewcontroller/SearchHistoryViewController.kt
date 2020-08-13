package com.jack.sample.pixabay.search.ui.viewcontroller

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jack.baselibrary.viewcontroller.BaseViewController
import com.jack.sample.pixabay.search.ui.recyclerview.SearchHistoryAdapter

class SearchHistoryViewController(
    private val recyclerView: RecyclerView,
    onItemClicked: ((item: String) -> Unit)? = null
) : BaseViewController<List<String>>(recyclerView) {


    private val adapter: SearchHistoryAdapter = SearchHistoryAdapter(onItemClicked)

    private var data: List<String>? = null

    init {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@SearchHistoryViewController.adapter
        }
    }

    override fun update(data: List<String>) {
        this.data?.let {
            val diffResult = DiffUtil.calculateDiff(Diff(it, data))
            diffResult.dispatchUpdatesTo(adapter)
        } ?: kotlin.run {
            adapter.setItems(data)
            adapter.notifyDataSetChanged()
        }
    }


}

private class Diff(val oldData: List<String>, val newData: List<String>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldData.size
    override fun getNewListSize() = newData.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldData[oldItemPosition] === (newData[newItemPosition])
}