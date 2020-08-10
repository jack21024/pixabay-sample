package com.jack.sample.pixabay.api

import com.jack.sample.pixabay.home.api.PixabayImagesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PixabayImagesApiTest {

    @Test
    fun testApiSuccess() = runBlocking {
        val api = PixabayImagesApi()
        val data = api.start()
        assert(data.result != null)
    }

}