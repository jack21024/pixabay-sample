package com.jack.sample.pixabay.home.ui.viewcontroller

import android.view.View
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
import com.jack.baselibrary.recyclerview.decotation.GridItemDecoration
import com.jack.baselibrary.recyclerview.decotation.LinearItemDecoration
import com.jack.baselibrary.utils.DisplayUtils
import com.jack.sample.pixabay.R

class MediumViewController(
    private val recyclerView: RecyclerView,
    private val loadingView: View? = null
) :
    BaseViewController<LiveData<PagedList<MediumCardItem>>>(recyclerView) {

    private var itemSpaceSize: Int = DisplayUtils.dp2px(ITEM_SPACE_SIZE).toInt()
    private var currentItemDecoration: RecyclerView.ItemDecoration? = null
    private var mediumLiveData: LiveData<PagedList<MediumCardItem>>? = null
    private var mediumCardItemList: PagedList<MediumCardItem>? = null

    private val observable = Observer<PagedList<MediumCardItem>> {
        setLoading(false)
        submitData(it)
    }

    init {
        itemSpaceSize =
            recyclerView.context.resources.getDimension(R.dimen.mg_list_item_space).toInt()
        setLoading(true)
        setLayoutStyle(MediumLayoutStyle.LIST)
    }

    override fun update(data: LiveData<PagedList<MediumCardItem>>) {
        setLoading(true)
        mediumLiveData?.removeObserver(observable)
        mediumLiveData = data.apply {
            recyclerView.scrollToPosition(0)
            recyclerView.context.let {
                if (it is LifecycleOwner) {
                    observe(it, observable)
                }
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        loadingView?.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun submitData(pagedList: PagedList<MediumCardItem>) {
        mediumCardItemList = pagedList
        recyclerView.adapter.let {
            it as MediumPagedAdapter
        }.run {
            submitList(pagedList)
        }
    }

    private fun updateLayoutManager(
        newAdapter: MediumPagedAdapter,
        newLayoutManager: RecyclerView.LayoutManager,
        visiblePos: Int
    ) {
        recyclerView.apply {
            adapter = newAdapter
            layoutManager = newLayoutManager
            scrollToPosition(visiblePos)
        }
        mediumCardItemList?.let {
            submitData(it)
        }
    }

    private fun updateItemDecoration(
        old: RecyclerView.ItemDecoration?,
        new: RecyclerView.ItemDecoration?
    ) {
        recyclerView.apply {
            old?.let {
                removeItemDecoration(it)
            }
            new?.let {
                addItemDecoration(it)
                currentItemDecoration = it
            }
        }
    }

    fun setLayoutStyle(style: MediumLayoutStyle) {
        val adapter = MediumPagedAdapter(style)
        var layoutManager = recyclerView.layoutManager
        var itemDeco = currentItemDecoration
        val currentPos =
            if (layoutManager is LinearLayoutManager) {
                layoutManager.findFirstVisibleItemPosition()
            } else 0

        when (style) {
            MediumLayoutStyle.LIST -> {
                layoutManager = LinearLayoutManager(view.context)
                itemDeco = LinearItemDecoration(itemSpaceSize)
            }
            MediumLayoutStyle.GRID -> {
                layoutManager = GridLayoutManager(view.context, GRID_SPAN_SIZE)
                itemDeco = GridItemDecoration(GRID_SPAN_SIZE, itemSpaceSize)
            }
        }
        updateItemDecoration(currentItemDecoration, itemDeco)
        updateLayoutManager(adapter, layoutManager, currentPos)
    }

    companion object {
        private const val ITEM_SPACE_SIZE = 1
        private const val GRID_SPAN_SIZE = 3
    }
}