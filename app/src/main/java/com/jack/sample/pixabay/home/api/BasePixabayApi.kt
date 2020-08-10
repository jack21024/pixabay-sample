package com.jack.sample.pixabay.home.api

import com.jack.baselibrary.network.BaseApi
import com.jack.sample.pixabay.net.PixabayService

abstract class BasePixabayApi<Result> :
    BaseApi<PixabayService, Result>(PixabayService.getInstance()) {
    init {
        parameters["key"] = "17794919-3a560aef61b94c4497b874684"
    }
}