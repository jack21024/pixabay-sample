package com.jack.sample.pixabay.home.api

import com.jack.sample.pixabay.home.data.entity.PixabayImageList
import com.jack.sample.pixabay.home.enums.MediumOrderType
import retrofit2.Response

class PixabayImageListApi : BasePixabayApi<PixabayImageList>() {

    fun init(pageNo: Int = 1, prePage: Int = 20) = this.apply {
        parameters["page"] = pageNo.toString()
        parameters["per_page"] = prePage.toString()
    }

    fun setKeyword(keyword: String? = null) = this.apply {
        keyword?.let {
            parameters["q"] = keyword
        }
    }

    fun setOrder(orderType: MediumOrderType?) = this.apply {
        parameters["order"] = orderType?.value ?: MediumOrderType.POPULAR.value
    }

    override suspend fun getResponse(): Response<PixabayImageList> {
        return service.getImages(parameters)
    }
}
