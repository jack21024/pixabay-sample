package com.jack.sample.pixabay.home.ui.viewcontroller

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jack.baselibrary.viewcontroller.BaseViewController
import com.jack.sample.pixabay.base.recyclerview.item.MediumCardItem
import com.jack.sample.pixabay.home.enums.MediumLayoutStyle
import com.jack.sample.pixabay.home.ui.adapter.MediumPagedAdapter

class MediumViewController(view: RecyclerView) :
    BaseViewController<LiveData<PagedList<MediumCardItem>>>(view) {

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
//        mediumCardItemList = data
//        adapter.submitList(data)

        mediumLiveData?.removeObserver(observable)
        mediumLiveData = data.apply {
            view.context.let {
                if(it is LifecycleOwner) {
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
        if (view is RecyclerView) {
            view.apply {
                adapter = this@MediumViewController.adapter
                layoutManager = newLayoutManager

            }
            mediumCardItemList?.let {
                submitData(it)
            }
        }
    }

    fun setLayoutStyle(style: MediumLayoutStyle) {
        adapter = MediumPagedAdapter(style)
        val layoutManager = when(style) {
            MediumLayoutStyle.LIST -> LinearLayoutManager(view.context)
            MediumLayoutStyle.GRID -> GridLayoutManager(view.context, 3)
        }
        updateLayoutManager(layoutManager)
//        mediumCardItemList?.let {
//            submitData(it)
//        }
    }

}