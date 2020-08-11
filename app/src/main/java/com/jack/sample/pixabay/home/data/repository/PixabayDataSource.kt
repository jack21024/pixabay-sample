package com.jack.sample.pixabay.home.data.repository

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.jack.sample.pixabay.base.recyclerview.item.MediumCardItem
import com.jack.sample.pixabay.home.api.PixabayImageListApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class PiaxbayImagesDataSourceFactory : DataSource.Factory<Int, MediumCardItem>() {
    override fun create(): DataSource<Int, MediumCardItem> {
        return PixabayImagesDataSource()
    }
}

class PixabayImagesDataSource : PageKeyedDataSource<Int, MediumCardItem>(),
    CoroutineScope by MainScope() {

    private val TAG = PixabayImagesDataSource::class.java.simpleName

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MediumCardItem>
    ) {
        fetchImageList(INITIAL_PAGE) {
            Log.d(TAG, "load init finished, images: ${it.size}")
            callback.onResult(it, null, INITIAL_PAGE + 1)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MediumCardItem>
    ) {
        val nextPage = params.key
        fetchImageList(nextPage) {
            Log.d(TAG, "load next finished, images: ${it.size}")
            callback.onResult(it, nextPage + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MediumCardItem>) =
        Unit

    private fun fetchImageList(
        pageNo: Int = INITIAL_PAGE,
        onResult: (imageList: List<MediumCardItem>) -> Unit
    ) {
        launch {
            PixabayImageListApi()
                .init(pageNo, PER_PAGE)
                .startWithResponse().let {
                    val mediumCardItems = it.result?.body()?.imageList?.map {
                        MediumCardItem(it)
                    } ?: emptyList()
                    onResult.invoke(mediumCardItems)
                }
        }
    }

    companion object {
        private const val INITIAL_PAGE = 1
        private const val PER_PAGE = 20
    }
}