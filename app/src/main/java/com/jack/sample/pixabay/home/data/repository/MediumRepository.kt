package com.jack.sample.pixabay.home.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jack.sample.pixabay.base.recyclerview.item.MediumCardItem
import com.jack.sample.pixabay.home.data.entity.PixabayImage

class MediumRepository {

    fun getImageList(): LiveData<PagedList<MediumCardItem>> {
        val source = PiaxbayImagesDataSourceFactory()
        val pagedListConf =
            PagedList.Config.Builder()
                .setPageSize(40)
                .setPrefetchDistance(20)
                .build()
        return LivePagedListBuilder<Int, MediumCardItem>(source, pagedListConf).build()
    }
}