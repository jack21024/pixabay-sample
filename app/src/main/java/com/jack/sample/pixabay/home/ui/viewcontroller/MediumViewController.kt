package com.jack.sample.pixabay.home.ui.viewcontroller

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jack.baselibrary.viewcontroller.BaseViewController
import com.jack.sample.pixabay.home.ui.recyclerview.item.MediumCardItem
import com.jack.sample.pixabay.home.enums.MediumLayoutStyle
import com.jack.sample.pixabay.home.ui.recyclerview.adapter.MediumPagedAdapter

class MediumViewController(private val recyclerView: RecyclerView) :
    BaseViewController<LiveData<PagedList<MediumCardItem>>>(recyclerView) {

    private var adapter = MediumPagedAdapter()
    private var mediumLiveData: LiveData<PagedList<MediumCardItem>>? = null
    private var mediumCardItemList: PagedList<MediumCardItem>? = null

    private val observable = Observer<PagedList<MediumCardItem>> {
        submitData(it)
    }

    init {
        updateLayoutManager(LinearLayoutManager(view.context))
    }

    override fun update(data: LiveData<PagedList<MediumCardItem>>) {
        mediumLiveData?.removeObserver(observable)
        mediumLiveData = data.apply {
            recyclerView.context.let {
                if (it is LifecycleOwner) {
                    observe(it, observable)
                }
            }
        }
    }

    private fun submitData(pagedList: PagedList<MediumCardItem>) {
        mediumCardItemList = pagedList
        adapter.submitList(pagedList)
    }

    private fun updateLayoutManager(newLayoutManager: RecyclerView.LayoutManager) {
        val oldLayoutManager = recyclerView.layoutManager
        val currentVisiblePos = when (oldLayoutManager) {
            is LinearLayoutManager -> oldLayoutManager.findFirstVisibleItemPosition()
            is GridLayoutManager -> oldLayoutManager.findFirstVisibleItemPosition()
            else -> 0
        }
        recyclerView.apply {
            adapter = this@MediumViewController.adapter
            layoutManager = newLayoutManager.apply { scrollToPosition(currentVisiblePos) }

        }
        mediumCardItemList?.let {
            submitData(it)
        }
    }

    fun setLayoutStyle(style: MediumLayoutStyle) {
        adapter = MediumPagedAdapter(style)
        val layoutManager = when (style) {
            MediumLayoutStyle.LIST -> LinearLayoutManager(view.context)
            MediumLayoutStyle.GRID -> GridLayoutManager(view.context, 3)
        }
        updateLayoutManager(layoutManager)
    }

}