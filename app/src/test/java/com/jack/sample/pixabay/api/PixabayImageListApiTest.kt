package com.jack.sample.pixabay.api

import com.jack.sample.pixabay.home.api.PixabayImageListApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PixabayImageListApiTest {

    @Test
    fun testApiSuccess() = runBlocking {
        val api = PixabayImageListApi()
        val data = api.start()
        assert(data.result != null)
    }

}