package com.jack.sample.pixabay.home.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jack.baselibrary.utils.createPagedDataSourceFactory
import com.jack.sample.pixabay.home.ui.recyclerview.item.MediumCardItem

class MediumRepository {

    fun getImageList(keyword: String? = null): LiveData<PagedList<MediumCardItem>> {
        val source =
            createPagedDataSourceFactory { PixabayImagesDataSource(keyword) }
        val pagedListConf =
            PagedList.Config.Builder()
                .setPageSize(40)
                .setPrefetchDistance(20)
                .build()
        return LivePagedListBuilder<Int, MediumCardItem>(source, pagedListConf).build()
    }
}