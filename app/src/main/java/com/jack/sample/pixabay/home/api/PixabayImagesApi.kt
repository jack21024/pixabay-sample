package com.jack.sample.pixabay.home.api

import com.jack.sample.pixabay.home.data.PixabayImagesData
import retrofit2.Response

class PixabayImagesApi : BasePixabayApi<PixabayImagesData>() {
    fun init(pageNo: Int = 1, prePage: Int = 20) = this.apply {
        parameters["page"] = pageNo.toString()
        parameters["per_page"] = prePage.toString()
    }

    override suspend fun getResponse(): Response<PixabayImagesData> {
        return service.getImages(parameters)
    }
}
